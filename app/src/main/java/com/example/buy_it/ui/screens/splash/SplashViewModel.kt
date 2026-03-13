package com.example.buy_it.ui.screens.splash

import androidx.lifecycle.ViewModel
import com.example.buy_it.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _navigateHome = MutableStateFlow(false)
    val navigateHome: StateFlow<Boolean> = _navigateHome

    init {
        checkUserLoggedIn()
    }

    private fun checkUserLoggedIn() {
        if (authRepository.currentUser != null) {
            _navigateHome.value = true
        } else {
            _navigateHome.value = false
        }
    }
}