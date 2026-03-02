package com.example.buy_it.ui.screens.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState

    fun onEmailChanged(newValue: String) {
        _uiState.update { it.copy(email = newValue, errorMessage = "") }
    }

    fun onPasswordChanged(newValue: String) {
        _uiState.update { it.copy(password = newValue, errorMessage = "") }
    }

    fun onRememberMeChanged() {
        _uiState.update { it.copy(isRememberMeChecked = !it.isRememberMeChecked) }
    }

    fun togglePasswordVisibility() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun toogleNavigation(){
        _uiState.update { it.copy(navigate = !it.navigate) }
    }

    fun loginButtonPressed(){
        if(
            _uiState.value.email.isNullOrEmpty() ||
            _uiState.value.password.isNullOrEmpty()
        ){
            _uiState.update { it.copy(mostrarMensaje = true, errorMessage = "Todos los campos son obligatorios") }
        } else {
            _uiState.update { it.copy(navigate = true) }
        }
    }
}