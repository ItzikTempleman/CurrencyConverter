package com.itzik.currency.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.itzik.currency.constants.getCurrencyNames

@Composable
fun AutoComplete(
) {
    val categories = getCurrencyNames
    var category by remember { mutableStateOf("") }
    var heightTextFields by remember { mutableStateOf(55.dp) }
    val textFieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }



    Column(modifier = Modifier
        .padding(30.dp)
        .fillMaxWidth()
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            expanded = false
        }
    ) {
        Text(
            modifier=Modifier.padding(start= 3.dp, bottom = 2.dp),
            text="Category",
            fontSize = 16.dp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )


    }


}