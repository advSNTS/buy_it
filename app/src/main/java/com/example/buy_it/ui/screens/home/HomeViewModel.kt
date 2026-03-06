package com.example.buy_it.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.buy_it.data.local.ReviewProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState

    fun getAllReviews(){
        _uiState.update { it.copy(reviews = ReviewProvider.feed) }
    }

    init {
        getAllReviews()
    }

}