package com.itzik.currency.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.itzik.currency.screens.ConvertScreen
import com.itzik.currency.screens.SplashScreen
import com.itzik.currency.viewmodels.UnitViewModel
import kotlinx.coroutines.CoroutineScope


const val SPLASH = "splashScreen"
const val HOME = "homeGraph"


@SuppressLint("SuspiciousIndentation")
@Composable
fun SetupNavGraph(
    navHostController: NavHostController,
    unitViewModel: UnitViewModel,
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
                ConvertScreen(
                    modifier = Modifier,
                    navHostController = navHostController,
                    unitViewModel = unitViewModel,
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
