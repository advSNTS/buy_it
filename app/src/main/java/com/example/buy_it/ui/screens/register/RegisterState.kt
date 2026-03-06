package com.example.buy_it.ui.screens.register

data class RegisterState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val acceptedTerms: Boolean = false,
    val mostrarPassword: Boolean = false,
    val mostrarConfirmPassword: Boolean = false,
)