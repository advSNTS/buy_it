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

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val productRepository: ProductRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(DetailState(
        currentUserId = authRepository.currentUser?.uid
    ))
    val uiState: StateFlow<DetailState> = _uiState

    fun onUserClicked(userId: String) {
        _uiState.update { it.copy(navigateToProfileUserId = userId) }
    }

    fun onNavigationHandled() {
        _uiState.update { it.copy(navigateToProfileUserId = null) }
    }

    fun loadProductDetail(productId: String) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val productResult = productRepository.getProductById(productId)
            val reviewsResult = productRepository.getProductReviews(productId)

            _uiState.update { it.copy(
                product = productResult.getOrNull(),
                reviews = reviewsResult.getOrDefault(emptyList()),
                isLoading = false
            ) }
        }
    }
}