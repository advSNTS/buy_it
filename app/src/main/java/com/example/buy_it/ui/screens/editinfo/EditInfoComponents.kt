package com.example.buy_it.ui.screens.editinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buy_it.R
import com.example.buy_it.ui.components.CompleteELipse

@Composable
fun PictureWithCircle(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ){
        CompleteELipse(
            sizeDraw = 175.dp
        )
        Image(
            modifier = Modifier.size(160.dp),
            painter = painterResource(R.drawable.logo),
            contentDescription = stringResource(R.string.logo_de_la_pagina)
        )
    }

}

@Composable
@Preview
fun PictureWithCirclePreview(){
    PictureWithCircle()
}