package com.example.buy_it.ui.screens.profile

import androidx.lifecycle.ViewModel
import com.example.buy_it.data.datasource.local.ReviewProvider
import com.example.buy_it.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        ProfileState(
            profileImage = authRepository.currentUser?.photoUrl?.toString()
        )
    )
    val uiState: StateFlow<ProfileState> = _uiState

    init {
        _uiState.update { currentState ->
            currentState.copy(
                reviews = ReviewProvider.feed,
                productosCount = "9",
                seguidoresCount = "1.6k",
            )
        }
    }
}