package com.example.buy_it.ui.screens.configuration

data class ConfigurationState(
    val searchQuery: String = "",
    val isAccountPrivate: Boolean = true,
    val blockedCount: Int = 11,
    val currentLanguage: String = "Español",
    val currentTheme: String = "Claro",
    val areNotificationsEnabled: Boolean = true
)