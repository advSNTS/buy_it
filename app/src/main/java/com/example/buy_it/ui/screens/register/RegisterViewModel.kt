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
        _uiState.update { it.copy(username = value) }
    }

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun onConfirmPasswordChange(value: String) {
        _uiState.update { it.copy(confirmPassword = value) }
    }

    fun onAcceptedTermsChange() {
        _uiState.update { it.copy(acceptedTerms = !it.acceptedTerms) }
    }

    fun onToggleMostrarPassword() {
        _uiState.update { it.copy(mostrarPassword = !it.mostrarPassword) }
    }

    fun onToggleMostrarConfirmPassword() {
        _uiState.update { it.copy(mostrarConfirmPassword = !it.mostrarConfirmPassword) }
    }

    fun onRegister() {
        val state = _uiState.value
        Log.d(
            "Envio de informacion",
            "username: ${state.username}, email: ${state.email}, " +
                    "contraseña: ${state.password}, confirmar contraseña: ${state.confirmPassword}, " +
                    "Recordar contraseña: ${state.acceptedTerms}"
        )
    }
}