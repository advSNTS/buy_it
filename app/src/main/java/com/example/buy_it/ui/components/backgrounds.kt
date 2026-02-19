package com.example.buy_it.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buy_it.R


@Composable
fun FondoBlanco(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.bgwhite))
    ){
        Elipse(
            radio = 150.dp,
            angulo = -71f,
            inicioGradiente = 0.1f,
            finGradiente = 0.7f,
            modifier = Modifier.align(Alignment.TopStart).offset(x = -32.dp).size(270.dp)
        )
        Elipse(
            radio = 100.dp,
            angulo = -71f,
            inicioGradiente = 0.1f,
            finGradiente = 0.7f,
            modifier = Modifier.align(Alignment.TopEnd).offset(x = 135.dp, y = 200.dp).size(287.dp)
        )
        Elipse(
            radio = 200.dp,
            angulo = -71f,
            inicioGradiente = 0.1f,
            finGradiente = 0.7f,
            modifier = Modifier.align(Alignment.TopStart).offset(x = -16.dp, y = 450.dp)
                .size(270.dp)
        )
    }
}


@Composable
@Preview()
fun FondoBlancoPreview(){
    FondoBlanco()
}

@Composable
fun FondoBlancoRegister(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.bgwhite))
    ){
        Elipse(
            radio = 120.dp,
            angulo = -71f,
            inicioGradiente = 0.1f,
            finGradiente = 0.7f,
            modifier = Modifier.align(Alignment.CenterStart).offset(x = -40.dp, y = -110.dp)
                .size(239.dp)
        )
        Elipse(
            radio = 100.dp,
            angulo = -71f,
            inicioGradiente = 0.1f,
            finGradiente = 0.7f,
            modifier = Modifier.align(Alignment.TopEnd).offset(x = 105.dp, y = 20.dp).size(270.dp)
        )
        Elipse(
            radio = 140.dp,
            angulo = -71f,
            inicioGradiente = 0.1f,
            finGradiente = 0.7f,
            modifier = Modifier.align(Alignment.BottomEnd).offset(x = 40.dp, y = -150.dp)
                .size(200.dp)
        )
    }
}


@Composable
@Preview()
fun FondoBlancoRegisterPreview(){
    FondoBlancoRegister()
}

@Composable
fun FondoBlancoEditInfo(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.bgwhite))
    ){
        Elipse(
            radio = 100.dp,
            angulo = -71f,
            inicioGradiente = 0.1f,
            finGradiente = 0.7f,
            modifier = Modifier.align(Alignment.Center).offset(x = -85.dp, y = -50.dp).size(239.dp)
        )
        Elipse(
            radio = 120.dp,
            angulo = -71f,
            inicioGradiente = 0.1f,
            finGradiente = 0.7f,
            modifier = Modifier.align(Alignment.TopEnd).offset(x = 35.dp, y = 20.dp).size(270.dp)
        )
        Elipse(
            radio = 140.dp,
            angulo = -71f,
            inicioGradiente = 0.1f,
            finGradiente = 0.7f,
            modifier = Modifier.align(Alignment.BottomEnd).offset(x = 40.dp, y = -150.dp)
                .size(200.dp)
        )
    }
}


@Composable
@Preview()
fun FondoBlancoEditInfoPreview(){
    FondoBlancoEditInfo()
}

@Composable
fun PanelGlass(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .width(352.dp)
            .height(800.dp)
            .background(
                color = colorResource(R.color.glasswhite),
                shape = RoundedCornerShape(size = 25.dp)
            ),
        contentAlignment = Alignment.TopCenter
    ){

    }
}

@Composable
@Preview()
fun PanelGlassPreview(){
    PanelGlass()
}

@Composable
fun ProfileCircles(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.bgwhite))
    ){
        Elipse(
            radio = 120.dp,
            angulo = -71f,
            inicioGradiente = 0.1f,
            finGradiente = 0.8f,
            modifier = Modifier.align(Alignment.TopCenter).offset(y = -40.dp)
        )
        Elipse(
            radio = 130.dp,
            angulo = -101f,
            inicioGradiente = 0.1f,
            finGradiente = 0.5f,
            modifier = Modifier.align(Alignment.TopStart).offset(x = -60.dp, y = -120.dp)
        )
        Elipse(
            radio = 130.dp,
            angulo = -71f,
            inicioGradiente = 0.1f,
            finGradiente = 0.5f,
            modifier = Modifier.align(Alignment.TopEnd).offset(x = 60.dp, y = -120.dp)
        )

    }
}

@Composable
@Preview()
fun ProfileCirclesPreview(){
    ProfileCircles()
}
