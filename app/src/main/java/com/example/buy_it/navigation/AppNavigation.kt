package com.example.buy_it.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.buy_it.ui.components.navbarPreview
import com.example.buy_it.ui.screens.configuration.Configuration
import com.example.buy_it.ui.screens.editinfo.EditInfo
import com.example.buy_it.ui.screens.login.Login
import com.example.buy_it.ui.screens.profile.Profile
import com.example.buy_it.ui.screens.register.Register
import com.example.buy_it.ui.theme.onErrorDark

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ){
        composable(route = "login"){
            Login(
                loginButtonPressed = {
                    navController.navigate("profile")
                },
                registerButtonPressed = {
                    navController.navigate("register")
                }
            )
        }

        composable(route = "register"){
            Register(
                registerButtonPressed = {
                    navController.navigate("profile")
                },
                onBackScreen = {
                    navController.navigate("login")
                },
            )
        }

        composable(route = "profile"){
            Profile(
                onProfileEdit = {
                    navController.navigate("editinfo")
                },
                onConfigurationEdit = {
                    navController.navigate("configuration")
                }
            )
        }

        composable(route = "editinfo"){
            EditInfo(
                onSaveChanges = {
                    navController.navigate("profile")
                }
            )
        }

        composable(route = "configuration"){
            Configuration(
                onBackPressed = {
                    navController.navigate("profile")
                }
            )
        }





    }
}

@Composable
@Preview(showBackground = true)
fun NavigationPreview() {
    //controlador vacio, solo para preview!!!!!!
    val navController = rememberNavController()

    AppNavigation(
        navController = navController
    )
}

