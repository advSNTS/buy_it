package com.example.buy_it.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.buy_it.ui.screens.configuration.Configuration
import com.example.buy_it.ui.screens.editinfo.EditInfo
import com.example.buy_it.ui.screens.login.Login
import com.example.buy_it.ui.screens.profile.Profile
import com.example.buy_it.ui.screens.register.Register
import com.example.buy_it.ui.screens.home.Home
import com.example.buy_it.ui.screens.detail.Detail
import com.example.buy_it.ui.screens.trends.Trends
import com.example.buy_it.ui.screens.comments.Comments
import com.example.buy_it.ui.screens.home.HomeViewModel
import com.example.buy_it.ui.screens.login.LoginViewModel

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Trends : Screen("trends")
    object EditInfo : Screen("editinfo")
    object Configuration : Screen("configuration")
    object Detail : Screen("detail/{productId}") {
        fun createRoute(productId: String) = "detail/$productId"
    }
    object Comments : Screen("comments/{productId}") {
        fun createRoute(productId: String) = "comments/$productId"
    }
    object ReviewEditorScreen : Screen("review_editor/{productId}") {
        fun createRoute(productId: String) = "review_editor/$productId"
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
        modifier = modifier
    ){
        composable(route = Screen.Login.route){
            val loginViewModel: LoginViewModel = viewModel()

            val state by loginViewModel.uiState.collectAsState()

            if(state.navigate){
                navController.navigate(Screen.Home.route){
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
                loginViewModel.toogleNavigation()
            }
            Login(
                loginViewModel = loginViewModel,
                onRegisterButtonPressed = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        composable(route = Screen.Register.route){
            Register(
                registerButtonPressed = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onBackScreen = {
                    navController.popBackStack()
                },
            )
        }

        composable(route = Screen.Profile.route){
            Profile(
                onProfileEdit = {
                    navController.navigate(Screen.EditInfo.route)
                },
                onConfigurationEdit = {
                    navController.navigate(Screen.Configuration.route)
                },
                onHomeClick = {
                    navController.navigate(Screen.Home.route) {
                        launchSingleTop = true
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onProfileClick = { /* Ya estamos aquí */ },
                onTrendsClick = {
                    navController.navigate(Screen.Trends.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(route = Screen.EditInfo.route){
            EditInfo(
                onSaveChanges = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screen.Configuration.route){
            Configuration(
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screen.Home.route) {
            val homeViewModel: HomeViewModel = viewModel()
            Home(
                onNotificationClick = { /* TODO */ },
                onHomeClick = { /* Ya estamos aquí */ },
                onProfileClick = { 
                    navController.navigate(Screen.Profile.route)
                },
                onTrendsClick = { 
                    navController.navigate(Screen.Trends.route)
                },
                onOpenDetail = { id -> 
                    navController.navigate(Screen.Detail.createRoute(id)) 
                },
                onAddReview = { id -> 
                    navController.navigate(Screen.ReviewEditorScreen.createRoute(id)) 
                },
                homeViewModel = homeViewModel
            )
        }

        composable(route = Screen.Comments.route) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId").orEmpty()
            Comments(
                productId = productId,
                onBackPressed = { navController.popBackStack() },
                onNotificationClick = { /* TODO */ },
                onHomeClick = { 
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onProfileClick = { navController.navigate(Screen.Profile.route) }
            )
        }

        composable(route = Screen.Detail.route) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId").orEmpty()
            Detail(
                productId = productId,
                onBackPressed = { navController.popBackStack() },
                onNotificationClick = { /* TODO */ },
                onOpenComments = { navController.navigate(Screen.Comments.createRoute(productId)) }
            )
        }

        composable(route = Screen.Trends.route) {
            Trends(
                onOpenDetail = { id -> navController.navigate(Screen.Detail.createRoute(id)) },
            )
        }

        composable(route = Screen.ReviewEditorScreen.route) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId").orEmpty()
            com.example.buy_it.ui.screens.revieweditor.ReviewEditor(
                productId = productId,
                onBackPressed = { navController.popBackStack() },
                onNotificationClick = { /* TODO */ },
                onPublish = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NavigationPreview() {
    val navController = rememberNavController()
    AppNavigation(navController = navController)
}
