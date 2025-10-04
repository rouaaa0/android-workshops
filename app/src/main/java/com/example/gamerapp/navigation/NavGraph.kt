package com.example.gamerapp.navigation

import ForgotPasswordScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gamerapp.LoginScreen
import com.example.gamerapp.ui.screens.OTPValidationScreen
import com.example.gamerapp.ui.screens.ResetPasswordScreen
import com.example.gamerapp.ui.screens.SignUpScreen
import com.example.gamerapp.ui.screens.SplashScreen
import com.example.gamerapp.ui.theme.activities.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") {
            SplashScreen(navController)
        }

        composable("login") {
            LoginScreen(navController)
        }

        composable("forgot") {
            ForgotPasswordScreen(navController)
        }

        composable(
            route = "otp/{code}",
            arguments = listOf(navArgument("code") { type = NavType.StringType })
        ) { backStackEntry ->
            val codeArg = backStackEntry.arguments?.getString("code").orEmpty()
            OTPValidationScreen(navController, codeArg)
        }

        composable("reset") {
            ResetPasswordScreen(navController)
        }

        composable("signup") {
            SignUpScreen(navController)
        }

        // 🏠 Home route qui charge HomeScreen avec BottomNav (News, Store, Profile)
        composable("home") {
            HomeScreen()
        }
    }
}
