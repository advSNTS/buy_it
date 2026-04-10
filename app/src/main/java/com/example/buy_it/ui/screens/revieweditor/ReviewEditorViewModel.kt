package com.example.buy_it.ui.screens.revieweditor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buy_it.data.repository.ProductRepository
import com.example.buy_it.data.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ReviewEditorViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository,
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReviewEditorState())
    val uiState: StateFlow<ReviewEditorState> = _uiState.asStateFlow()

    private fun recalculate(state: ReviewEditorState): ReviewEditorState {
        return state.copy(
            canPublish = state.likeChoice != LikeChoice.None &&
                    state.opinion.trim().isNotEmpty()
        )
    }

    fun onLikeChoiceChange(likeChoice: LikeChoice) {
        _uiState.update { current ->
            recalculate(current.copy(likeChoice = likeChoice))
        }
    }

    fun onOpinionChange(opinion: String) {
        _uiState.update { current ->
            recalculate(current.copy(opinion = opinion))
        }
    }

    fun loadEditor(productId: String, reviewId: String?) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, errorMessage = null)
            }

            val productResult = productRepository.getProductById(productId)

            if (productResult.isFailure) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = productResult.exceptionOrNull()?.message ?: "Error al cargar el producto"
                    )
                }
                return@launch
            }

            val product = productResult.getOrThrow()

            _uiState.update {
                recalculate(
                    it.copy(
                        productName = product.name,
                        productImage = product.image,
                        isLoading = false,
                        errorMessage = null
                    )
                )
            }

            if (!reviewId.isNullOrBlank()) {
                loadReviewForEdit(reviewId)
            }
        }
    }

    private fun loadReviewForEdit(reviewId: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, errorMessage = null)
            }

            val result = reviewRepository.getReviewById(reviewId)

            if (result.isSuccess) {
                val review = result.getOrThrow()
                _uiState.update {
                    recalculate(
                        it.copy(
                            reviewId = review.id,
                            likeChoice = if (review.like) LikeChoice.Like else LikeChoice.Dislike,
                            opinion = review.review,
                            isLoading = false,
                            errorMessage = null,
                            isEditMode = true
                        )
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = result.exceptionOrNull()?.message ?: "Error al cargar la reseña"
                    )
                }
            }
        }
    }

    fun submitReview(productId: String) {
        viewModelScope.launch {
            val state = _uiState.value

            _uiState.update {
                it.copy(isLoading = true, errorMessage = null, navigateBack = false)
            }

            val likeValue = state.likeChoice == LikeChoice.Like

            val result = if (state.reviewId == null) {
                reviewRepository.createReview(
                    productId = productId,
                    like = likeValue,
                    comment = state.opinion.trim()
                )
            } else {
                reviewRepository.updateReview(
                    id = state.reviewId,
                    productId = productId,
                    like = likeValue,
                    comment = state.opinion.trim()
                )
            }

            if (result.isSuccess) {
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = null, navigateBack = true)
                }
            } else {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = result.exceptionOrNull()?.message ?: "Error al guardar la reseña"
                    )
                }
            }
        }
    }

    fun deleteReview() {
        val reviewId = _uiState.value.reviewId ?: return

        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, errorMessage = null, navigateBack = false)
            }

            val result = reviewRepository.deleteReview(reviewId)

            if (result.isSuccess) {
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = null, navigateBack = true)
                }
            } else {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = result.exceptionOrNull()?.message ?: "Error al eliminar la reseña"
                    )
                }
            }
        }
    }

    fun onNavigatedBack() {
        _uiState.update { it.copy(navigateBack = false) }
    }
}
