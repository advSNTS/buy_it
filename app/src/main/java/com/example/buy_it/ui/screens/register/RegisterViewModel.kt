package com.example.buy_it.ui.screens.register

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterState())
    val uiState: StateFlow<RegisterState> = _uiState.asStateFlow()

    fun onUsernameChange(value: String) {
        _uiState.update { it.copy(username = value, errorMessage = "") }
    }

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value, errorMessage = "") }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value, errorMessage = "") }
    }

    fun onConfirmPasswordChange(value: String) {
        _uiState.update { it.copy(confirmPassword = value, errorMessage = "") }
    }

    fun onAcceptedTermsChange() {
        _uiState.update { it.copy(acceptedTerms = !it.acceptedTerms, errorMessage = "") }
    }

    fun onToggleMostrarPassword() {
        _uiState.update { it.copy(mostrarPassword = !it.mostrarPassword) }
    }

    fun onToggleMostrarConfirmPassword() {
        _uiState.update { it.copy(mostrarConfirmPassword = !it.mostrarConfirmPassword) }
    }

    fun onRegister() {
        val state = _uiState.value
        if (
            state.username.isBlank() ||
            state.email.isBlank() ||
            state.password.isBlank() ||
            state.confirmPassword.isBlank() ||
            !state.acceptedTerms
        ) {
            _uiState.update { it.copy(mostrarMensaje = true, errorMessage = "Todos los campos son obligatorios") }
        } else if (state.password != state.confirmPassword) {
            _uiState.update { it.copy(mostrarMensaje = true, errorMessage = "Las contraseñas no coinciden") }
        } else {
            Log.d("Register", "Registrando usuario: ${state.username}")
            _uiState.update { it.copy(navigateToHome = true) }
        }
    }

    fun onBackClicked() {
        _uiState.update { it.copy(navigateBack = true) }
    }

    fun onNavigationHandled() {
        _uiState.update { it.copy(navigateToHome = false, navigateBack = false) }
    }
}
