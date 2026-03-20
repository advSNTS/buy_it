package com.example.buy_it.ui.screens.detail

import androidx.lifecycle.ViewModel
import com.example.buy_it.data.datasource.local.ProductProvider
import com.example.buy_it.data.datasource.local.ReviewProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class DetailViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(DetailState())
    val uiState: StateFlow<DetailState> = _uiState

    fun loadProductDetail(productId: String) {
        _uiState.update { it.copy(isLoading = true) }
        
        val product = ProductProvider.byId(productId)
        val allReviews = ReviewProvider.feed
        
        val filteredReviews = if (product != null) {
            allReviews.filter { it.product == product.name }.ifEmpty { allReviews }
        } else {
            emptyList()
        }

        _uiState.update { it.copy(
            product = product,
            reviews = filteredReviews,
            isLoading = false
        ) }
    }
}