package com.example.buy_it.ui.screens.prices

import androidx.lifecycle.ViewModel
import com.example.buy_it.data.datasource.local.PricedItemsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class PricesViewModel @Inject constructor() : ViewModel() {
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