package com.example.buy_it.ui.screens.trends

import androidx.lifecycle.ViewModel
import com.example.buy_it.data.local.TrendProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class TrendsViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(TrendsState())
    val uiState: StateFlow<TrendsState> = _uiState

    init {
        val initialTrends = TrendProvider.trends
        _uiState.update { it.copy(trends = initialTrends, filteredTrends = initialTrends) }
    }

    fun onSearchQueryChanged(newQuery: String) {
        _uiState.update { currentState ->
            val filtered = if (newQuery.isBlank()) {
                currentState.trends
            } else {
                currentState.trends.filter { it.name.contains(newQuery, ignoreCase = true) }
            }
            currentState.copy(
                searchQuery = newQuery,
                filteredTrends = filtered
            )
        }
    }
}