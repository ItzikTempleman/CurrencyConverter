package com.itzik.currency.screens.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

    val lightBlue = colorResource(id = R.color.light_yellow)
    val darkBlue = colorResource(id = R.color.strong_yellow)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth().height(30.dp)
                .background(color = colorResource(id = R.color.strong_yellow))
        ) {        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .graphicsLayer(
                shape = RoundedCornerShape(20.dp),
                clip = true
            )
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(lightBlue, darkBlue),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
    ) {

    }
}






