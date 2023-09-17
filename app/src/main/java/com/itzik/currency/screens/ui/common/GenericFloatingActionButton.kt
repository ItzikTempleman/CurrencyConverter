package com.itzik.currency.screens.ui.common

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonElevation
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GenericFloatingActionButton(

    modifier: Modifier,
    onClick: () -> Unit,
    backgroundColor: Color,

    painter: Painter,
    contentDescription: String?,
    tint: Color,
) {
    FloatingActionButton(

        modifier = modifier,
        onClick = onClick,
        backgroundColor = backgroundColor
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
            tint = tint
        )
    }
}