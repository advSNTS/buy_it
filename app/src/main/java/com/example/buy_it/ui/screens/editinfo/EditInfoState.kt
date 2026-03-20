package com.example.buy_it.ui.screens.editinfo

data class EditInfoState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val mostrarPassword: Boolean = false,
    val profileImage: String? = ""
)