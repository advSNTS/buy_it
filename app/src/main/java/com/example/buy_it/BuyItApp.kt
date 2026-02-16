package com.example.buy_it

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import androidx.navigation.compose.rememberNavController
import com.example.buy_it.navigation.AppNavigation
import com.example.buy_it.ui.screens.login.Login

@Composable
fun BuyIt(
    modifier: Modifier = Modifier
){
    Scaffold(
    ) {

        val navController = rememberNavController()


        AppNavigation(
            navController,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
@Preview
fun BuyItPreview(){
    BuyIt()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyItTopAppBar(

){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "buy it.",

                style = TextStyle(
                    fontSize = 32.sp,
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
                ),
                modifier = Modifier
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BuyItTopAppBarPreview(){
    BuyItTopAppBar()

}