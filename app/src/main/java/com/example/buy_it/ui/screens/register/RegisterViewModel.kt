package com.example.buy_it.ui.screens.register

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterState())
    val uiState: StateFlow<RegisterState> = _uiState

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
            state.username.isNullOrEmpty() ||
            state.email.isNullOrEmpty() ||
            state.password.isNullOrEmpty() ||
            state.confirmPassword.isNullOrEmpty() ||
            !state.acceptedTerms
        ) {
            _uiState.update { it.copy(mostrarMensaje = true, errorMessage = "Todos los campos son obligatorios") }
        } else {
            Log.d(
                "Envio de informacion",
                "username: ${state.username}, email: ${state.email}, " +
                        "contraseña: ${state.password}, confirmar contraseña: ${state.confirmPassword}, " +
                        "Recordar contraseña: ${state.acceptedTerms}"
            )
        }
    }
}