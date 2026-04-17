package com.example.buy_it.ui.screens.createreview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buy_it.data.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CreateReviewUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class CreatereviewViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateReviewUiState())
    val uiState: StateFlow<CreateReviewUiState> = _uiState.asStateFlow()

    fun createReview(
        productId: String,
        like: Boolean,
        comment: String
    ) {
        if (comment.isBlank()) {
            _uiState.value = CreateReviewUiState(
                errorMessage = "El comentario no puede estar vacío"
            )
            return
        }

        viewModelScope.launch {
            _uiState.value = CreateReviewUiState(isLoading = true)

            val result = reviewRepository.createReview(
                productId = productId,
                like = like,
                comment = comment
            )

            _uiState.value = if (result.isSuccess) {
                CreateReviewUiState(isSuccess = true)
            } else {
                CreateReviewUiState(
                    errorMessage = result.exceptionOrNull()?.message ?: "Error al crear el review"
                )
            }
        }
    }

    fun clearState() {
        _uiState.value = CreateReviewUiState()
    }
}