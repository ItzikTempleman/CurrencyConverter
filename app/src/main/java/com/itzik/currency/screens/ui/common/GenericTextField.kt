package com.itzik.currency.screens.ui.common

import android.media.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itzik.currency.R
import com.itzik.currency.utils.removeParenthesis


@Composable
fun GenericTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier,
    currencyList: List<Pair<String, String>>,
    isKeyTypeNumOnly: Boolean
) {


    var selectedItem by remember { mutableStateOf("") }
    var isContextMenuVisible by rememberSaveable { mutableStateOf(false) }
    val longCurrencyName = currencyList.map { it.second }


    var icon: Painter? = null
    if (!isKeyTypeNumOnly) {
        icon = if (isContextMenuVisible) {
            painterResource(id = R.drawable.close)
        } else {
            painterResource(id = R.drawable.arrow_down)
        }
    }



    OutlinedTextField(
        keyboardOptions = if (isKeyTypeNumOnly) 
            KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        textStyle = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.ExtraBold,
            color = colorResource(id = R.color.blue),
            fontSize = 24.sp
        ),
        value = value,
        onValueChange = {
            onValueChange(it)
        },

        modifier = modifier

            .padding(vertical = 2.dp, horizontal = 12.dp)
            .fillMaxWidth(),
        label = {
            Text(
                text = label,
                fontSize = 20.sp
            )
        },
        leadingIcon = {
            if (icon != null) {
                Icon(
                    icon,
                    null,
                    Modifier.clickable {
                    isContextMenuVisible = !isContextMenuVisible
                },
                    tint = Color.DarkGray)
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.Black,
            textColor = Color.Black,
            backgroundColor =  if (icon != null) Color.Transparent else  Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Black,
            focusedLabelColor = colorResource(id = R.color.black)
        )
    )

    DropdownMenu(
        expanded = isContextMenuVisible,
        onDismissRequest = {
            isContextMenuVisible = false
        },
        modifier = Modifier
            .wrapContentWidth()
    ) {
        currencyList.forEach {
            DropdownMenuItem(onClick = {
                selectedItem = it.toString()
                isContextMenuVisible = false
                onValueChange(selectedItem)
            }) {
                Text(text = removeParenthesis(it.toString()))
            }
        }
    }
}








