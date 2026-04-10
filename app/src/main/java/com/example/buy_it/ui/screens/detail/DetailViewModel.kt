package com.example.buy_it.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buy_it.data.datasource.local.ProductProvider
import com.example.buy_it.data.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailState())
    val uiState: StateFlow<DetailState> = _uiState

    fun loadProductDetail(productId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val product = ProductProvider.byId(productId)
            val reviewsResult = reviewRepository.getReviewsByProductId(productId)

            val reviews = if (reviewsResult.isSuccess) {
                reviewsResult.getOrDefault(emptyList())
            } else {
                println(reviewsResult.exceptionOrNull()?.message)
                emptyList()
            }

            _uiState.update {
                it.copy(
                    product = product,
                    reviews = reviews,
                    isLoading = false
                )
            }
        }
    }
}