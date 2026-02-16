package com.example.buy_it

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import com.example.buy_it.ui.screens.editinfo.EditInfo
import com.example.buy_it.ui.screens.login.Login
import com.example.buy_it.ui.screens.profile.Profile
import com.example.buy_it.ui.screens.register.Register
import com.example.buy_it.ui.theme.Buy_itTheme
import com.example.buy_it.ui.theme.bodyFontFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Buy_itTheme{
                Scaffold(

                ) {
                    Login(
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }
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