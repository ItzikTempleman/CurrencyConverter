package com.itzik.currency.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.currency.navigation.HomeGraph
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {

    LaunchedEffect(key1 = true) {

        delay(0)
        navHostController.popBackStack()
        navHostController.navigate(HomeGraph.HomeScreen.route)
    }

}