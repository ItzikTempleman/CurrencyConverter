package com.itzik.currency.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

import com.itzik.currency.screens.MainScreen
import com.itzik.currency.screens.SplashScreen
import com.itzik.currency.viewmodels.CurrencyViewModel
import kotlinx.coroutines.CoroutineScope


const val SPLASH = "splashScreen"
const val HOME = "homeGraph"


@SuppressLint("SuspiciousIndentation")
@Composable
fun SetupNavGraph(
    navHostController: NavHostController,
    currencyViewModel: CurrencyViewModel,
    coroutineScope: CoroutineScope,
) {
    NavHost(
        navController = navHostController,
        startDestination = SPLASH
    ) {
        navigation(
            startDestination = SplashGraph.Splash.route,
            route = SPLASH
        ) {


            composable(route = SplashGraph.Splash.route) {
                SplashScreen(navHostController)
            }
        }
        navigation(
            startDestination = HomeGraph.HomeScreen.route,
            route = HOME
        ) {
            composable(route= HomeGraph.HomeScreen.route){
                MainScreen(
                    modifier = Modifier,
                    currencyViewModel = currencyViewModel,
                    coroutineScope =coroutineScope
                )
            }
        }
    }


}

sealed class SplashGraph(val route: String) {
    object Splash : SplashGraph(route = "splash")
}

sealed class HomeGraph(val route: String) {
    object HomeScreen : HomeGraph(route = "home")
}
