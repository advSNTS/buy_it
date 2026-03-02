package com.example.buy_it

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import com.example.buy_it.ui.components.TopBarBackground

@Composable
fun BuyIt(
    modifier: Modifier = Modifier
){
    val navController = rememberNavController()
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route

    val showTopBar = currentRoute != null &&
                 currentRoute != Screen.Login.route && 
                 currentRoute != Screen.Register.route &&
                 currentRoute != Screen.Profile.route &&
                 currentRoute != Screen.EditInfo.route &&
                 currentRoute != Screen.Configuration.route

    val showNavBar = currentRoute != null &&
            currentRoute != Screen.Login.route &&
            currentRoute != Screen.Register.route &&
            currentRoute != Screen.Comments.route

    Box(modifier = Modifier.fillMaxSize()) {
        if (showTopBar) {
            MainBackground()
        }
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                if (showTopBar) {
                    BuyItTopAppBar()
                }
            },
            bottomBar = {
                if (showNavBar) {
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyItTopAppBar(){
    // 3. Quitamos el TopBarBackground de aquí para que use el MainBackground global
    // O si quieres círculos extra, asegúrate de que el contenedor de la TopBar sea transparente
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent, // Transparente para fusión total
            scrolledContainerColor = Color.Transparent
        ),
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
                        end = Offset(80f, -40f)
                    ),
                    textAlign = TextAlign.Center
                )
            )
        }
    )
}
