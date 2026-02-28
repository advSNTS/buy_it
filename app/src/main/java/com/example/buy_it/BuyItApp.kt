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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.buy_it.navigation.AppNavigation
import com.example.buy_it.navigation.Screen
import com.example.buy_it.ui.components.BarNav
import com.example.buy_it.ui.components.MainBackground

@Composable
fun BuyIt(
    modifier: Modifier = Modifier
){
    val navController = rememberNavController()
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route

    val showBar = currentRoute != null && 
                 currentRoute != Screen.Login.route && 
                 currentRoute != Screen.Register.route


    Scaffold(
        topBar = {
            if (showBar) {
                BuyItTopAppBar()
            }
        },
        bottomBar = {
            if (showBar) {
                BarNav(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    onHomeClick = { 
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    },
                    onBuscarClick = { navController.navigate(Screen.Trends.route) },
                    onAddClick = { /* Acción para agregar */ },
                    onProfileClick = { navController.navigate(Screen.Profile.route) }
                )
            }
        }
    ) { paddingValues ->
        AppNavigation(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
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
                    fontSize = 26.sp,
                    fontWeight = FontWeight(510),
                    brush = Brush.linearGradient(
                        colorStops = arrayOf(
                            0.17f to colorResource(R.color.graybluebuyit),
                            1f to colorResource(R.color.navybluebuyit)
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(80f, -40f) //x es el seno y y el cosenop
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
