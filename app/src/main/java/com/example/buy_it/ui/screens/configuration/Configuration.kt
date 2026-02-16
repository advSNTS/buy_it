package com.example.buy_it.ui.screens.configuration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.buy_it.R

@Composable
fun Configuration(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
){
    Box(){

        Text(
            text = "Configuraciones",

            style = TextStyle(
                fontSize = 50.sp,
                fontWeight = FontWeight(510),
                brush = Brush.linearGradient(
                    colorStops = arrayOf(
                        0.17f to colorResource(R.color.graybluebuyit),
                        1f to colorResource(R.color.navybluebuyit)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0.5299f*1000f, -0.848f*100f) //x es el seno y y el cosenop
                ), //libreria de gradientes
                textAlign = TextAlign.Center
            ), modifier = modifier.fillMaxWidth()
        )
    }

}

@Composable
@Preview(showBackground = true)
fun ConfigurationPreview(
    modifier: Modifier = Modifier
){
    Configuration({})
}