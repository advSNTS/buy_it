package com.example.buy_it.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.buy_it.data.local.ReviewProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState

    fun getAllReviews(){
        _uiState.update { it.copy(reviews = ReviewProvider.feed) }
    }

    init {
        getAllReviews()
    }

}