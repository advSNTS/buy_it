package com.example.buy_it.data.dtos

data class RegisterUserDto(
    val username: String,
    val name: String,
    val FCMToken: String
)
