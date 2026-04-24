package com.example.buy_it.ui.screens.trends

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buy_it.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class TrendsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TrendsState())
    val uiState: StateFlow<TrendsState> = _uiState

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            // productRepository.seedFirestoreProductsIfNeeded()

            val result = productRepository.getAllProducts()

            if (result.isSuccess) {
                val products = result.getOrDefault(emptyList())

                _uiState.update {
                    it.copy(
                        products = products,
                        filteredProducts = products,
                        isLoading = false
                    )
                }
            } else {
                Log.e("TrendsViewModel", "Error cargando catálogo: ${result.exceptionOrNull()?.message}")
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun onSearchQueryChanged(newQuery: String) {
        _uiState.update { currentState ->
            val filtered = if (newQuery.isBlank()) {
                currentState.products
            } else {
                currentState.products.filter { 
                    it.name.contains(newQuery, ignoreCase = true) ||
                    it.description.contains(newQuery, ignoreCase = true)
                }
            }
            currentState.copy(
                searchQuery = newQuery,
                filteredProducts = filtered
            )
        }
    }
}
