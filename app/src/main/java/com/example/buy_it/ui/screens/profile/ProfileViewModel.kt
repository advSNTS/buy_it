package com.example.buy_it.ui.screens.profile

import androidx.lifecycle.ViewModel
import com.example.buy_it.data.local.ProfileItemsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileState())
    val uiState: StateFlow<ProfileState> = _uiState

    init {
        _uiState.update { currentState ->
            currentState.copy(
                profileItems = ProfileItemsProvider.itemsFromProfile,
                productosCount = "9",
                seguidoresCount = "1.6k",
                seguidosCount = "128"
            )
        }
    }
}