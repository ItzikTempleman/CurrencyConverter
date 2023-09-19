package com.itzik.currency.screens.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.itzik.currency.R

@Composable
fun CustomImage() {

    val orange = colorResource(id = R.color.orange)
    val yellow = colorResource(id = R.color.yellow)
    val lightYellow = colorResource(id = R.color.yellow)
    val white = colorResource(id = R.color.white)


    val brush = Brush.horizontalGradient(listOf(orange, yellow))
    val brush2 = Brush.verticalGradient(listOf(lightYellow, yellow))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {}

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .graphicsLayer(
                clip = true
            )
            .background(
                brush2
            )
    ) {}

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(615.dp)
            .graphicsLayer(
                shape = RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp, topEnd = 350.dp),
                clip = true
            )
            .background(
                brush
            )
    ) {}

}






