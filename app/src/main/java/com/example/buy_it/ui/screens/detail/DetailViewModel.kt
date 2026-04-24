package com.example.buy_it.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buy_it.data.repository.AuthRepository
import com.example.buy_it.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.buy_it.data.repository.ReviewRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val productRepository: ProductRepository,
    private val reviewRepository: ReviewRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(DetailState(
        currentUserId = authRepository.currentUser?.uid
    ))
    val uiState: StateFlow<DetailState> = _uiState
    private var reviewsJob: Job? = null

    fun onUserClicked(userId: String) {
        _uiState.update { it.copy(navigateToProfileUserId = userId) }
    }

    fun onNavigationHandled() {
        _uiState.update { it.copy(navigateToProfileUserId = null) }
    }

    fun isReviewOwner(reviewUserId: String): Boolean {
        return authRepository.currentUser?.uid == reviewUserId
    }

    fun loadProductDetail(productId: String) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val productResult = productRepository.getProductById(productId)

            _uiState.update {
                it.copy(
                    product = productResult.getOrNull(),
                    isLoading = false
                )
            }

            listenReviews(productId)
        }
    }

    fun onReviewLikeClicked(reviewId: String) {
        viewModelScope.launch {
            val result = reviewRepository.sendReviewLike(reviewId)

            if (result.isSuccess) {
                _uiState.update { state ->
                    state.copy(
                        reviews = state.reviews.map { review ->
                            if (review.id == reviewId) {
                                val alreadyLiked = review.likedByCurrentUser

                                review.copy(
                                    likedByCurrentUser = !alreadyLiked,
                                    likesCount = if (alreadyLiked) {
                                        (review.likesCount - 1).coerceAtLeast(0)
                                    } else {
                                        review.likesCount + 1
                                    }
                                )
                            } else {
                                review
                            }
                        }
                    )
                }
            }
        }
    }

    private fun listenReviews(productId: String) {
        reviewsJob?.cancel()

        reviewsJob = viewModelScope.launch {
            reviewRepository.listenReviewsByProductId(productId)
                .catch { error ->
                    android.util.Log.e(
                        "DetailViewModel",
                        "Error escuchando reviews: ${error.message}"
                    )
                }
                .collectLatest { reviews ->
                    _uiState.update {
                        it.copy(reviews = reviews)
                    }
                }
        }
    }
}