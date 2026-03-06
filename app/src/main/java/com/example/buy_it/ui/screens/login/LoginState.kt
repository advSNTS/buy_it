package com.example.buy_it.ui.screens.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isRememberMeChecked: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val navigate: Boolean = false,
    val mostrarMensaje: Boolean = false,
    val errorMessage: String = ""
)