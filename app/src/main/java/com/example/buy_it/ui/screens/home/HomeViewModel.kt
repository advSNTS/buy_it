package com.example.buy_it.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buy_it.data.datasource.local.ReviewProvider
import com.example.buy_it.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState

    fun getAllProducts(){
        viewModelScope.launch {
            val result = productRepository.getAllProducts()
            Log.d("Home", "All Prods: $result")
            if (result.isSuccess){
                _uiState.update {
                    it.copy(
                        products = result.getOrDefault(emptyList())
                    )
                }
            }
        }
    }

    init {
        getAllProducts()
    }

}