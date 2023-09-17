package com.itzik.currency.screens.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.itzik.currency.R

@Composable
fun CustomImage() {

    val turquoise = colorResource(id = R.color.turquoise)
    val lightPink = colorResource(id = R.color.light_pink)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {}

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp).padding(4.dp)
            .graphicsLayer(
                shape = RoundedCornerShape(12.dp),
                clip = true
            )
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(turquoise, lightPink),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
    ) {

    }
}






