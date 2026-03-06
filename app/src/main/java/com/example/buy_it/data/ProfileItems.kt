package com.example.buy_it.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.Modifier
import com.example.buy_it.R

data class ProfileItems(
    @DrawableRes val img: Int,
    val descripcion: String,
)