package com.itzik.currency.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.currency.viewmodels.UnitViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun ConvertScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    unitViewModel: UnitViewModel,
    coroutineScope: CoroutineScope,
) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Main screen",
            fontSize = 28.sp
        )
    }
}