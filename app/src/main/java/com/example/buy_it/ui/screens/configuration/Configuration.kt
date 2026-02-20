package com.example.buy_it.ui.screens.configuration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buy_it.R

@Composable
fun Configuration(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ){
        Image(
            painter = painterResource(R.drawable.arrowleft),
            contentDescription = stringResource(R.string.volver),
            modifier = Modifier
                .padding(start = 10.dp, top = 20.dp, bottom = 15.dp)
                .size(30.dp)
        )

        Text(
            text = "Configuraciones",

            style = TextStyle(
                fontSize = 45.sp,
                fontWeight = FontWeight(510),
                brush = Brush.linearGradient(
                    colorStops = arrayOf(
                        0.17f to colorResource(R.color.graybluebuyit),
                        1f to colorResource(R.color.navybluebuyit)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0.5299f*1000f, -0.848f*100f) //x es el seno y y el cosenop
                ),
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp)
        )
        SearchBar()
        SubsectionText("Quien pude ver tus reseñas", Modifier.padding(start = 20.dp, top = 15.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun ConfigurationPreview(
    modifier: Modifier = Modifier
){
    Configuration({})
}