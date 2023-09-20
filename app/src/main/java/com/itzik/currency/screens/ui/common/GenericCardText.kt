package com.itzik.currency.screens.ui.common

import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.TextUnit
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun GenericCardText(
    backgroundColor: Color,
    modifier: Modifier,
    constraintModifier: Modifier,
    text: String,
    textModifier: Modifier,
    fontSize: TextUnit,
    textColor: Color,
    iconModifier: Modifier?,
    painter: Painter?,
    contentDescription: String?,
    tint: Color?
) {
    Card(
        backgroundColor = backgroundColor,
        modifier = modifier,
    ) {
        ConstraintLayout(
            modifier = constraintModifier
        ) {
            Text(
              modifier=  textModifier,
                text = text,
                fontSize = fontSize,
                color = textColor
            )
            if (tint != null) {
                if (iconModifier != null) {
                    if (painter != null) {
                        Icon(
                            modifier = iconModifier,
                            painter = painter,
                            contentDescription = contentDescription,
                            tint = tint
                        )
                    }
                }
            }
        }
    }
}