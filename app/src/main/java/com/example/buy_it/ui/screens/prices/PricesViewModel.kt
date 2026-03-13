package com.example.buy_it.ui.screens.prices

import androidx.lifecycle.ViewModel
import com.example.buy_it.data.local.PricedItemsProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PricesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PricesState())
    val uiState: StateFlow<PricesState> = _uiState

    init {
        loadPrices()
    }

    private fun loadPrices() {
        _uiState.update { it.copy(isLoading = true) }
        val items = PricedItemsProvider.pricedItems
        _uiState.update { it.copy(
            pricedItems = items,
            isLoading = false
        ) }
    }
}