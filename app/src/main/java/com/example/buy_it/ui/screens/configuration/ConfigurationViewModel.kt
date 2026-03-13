package com.example.buy_it.ui.screens.configuration

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ConfigurationViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ConfigurationState())
    val uiState: StateFlow<ConfigurationState> = _uiState

    fun onSearchQueryChanged(newQuery: String) {
        _uiState.update { it.copy(searchQuery = newQuery) }
    }

    fun toggleAccountPrivacy() {
        _uiState.update { it.copy(isAccountPrivate = !it.isAccountPrivate) }
    }

    fun onLanguageChanged(newLanguage: String) {
        _uiState.update { it.copy(currentLanguage = newLanguage) }
    }

    fun onThemeChanged(newTheme: String) {
        _uiState.update { it.copy(currentTheme = newTheme) }
    }
}