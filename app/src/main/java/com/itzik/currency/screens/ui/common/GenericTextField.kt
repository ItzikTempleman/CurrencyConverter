package com.itzik.currency.screens.ui.common

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize


@Composable
fun GenericTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier,
    currencyList: List<Pair<String, String>>,
    isKeyTypeNumOnly: Boolean,
) {
    val pattern = remember { Regex("^\\d+\$") }
    var textFiledSize by remember { mutableStateOf(Size.Zero) }

    var isContextMenuVisible by rememberSaveable { mutableStateOf(false) }
    var icon: ImageVector? = null
    if (!isKeyTypeNumOnly) {
        icon = if (isContextMenuVisible) {
            Icons.Filled.KeyboardArrowUp
        } else {
            Icons.Filled.KeyboardArrowDown
        }
    }

    Log.d("TAG", "value: $value")
    Log.d("TAG", "currencyList: $currencyList")

    var selectedItem by remember { mutableStateOf("") }
    /*var input by remember {
        mutableStateOf(value)
    }*/
    OutlinedTextField(
        keyboardOptions = if (isKeyTypeNumOnly) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        singleLine = true,
        textStyle = TextStyle(fontSize = 20.sp),
        value = value,
        onValueChange = {
            if (isKeyTypeNumOnly) {
                if (it.matches(pattern)){
                    onValueChange(it)
                }
            } else onValueChange(it)
            Log.d("TAG", "'input' onValueChange: $value")
        },

        modifier = modifier
            .padding(vertical = 2.dp, horizontal = 12.dp)
            .fillMaxWidth()
            .onGloballyPositioned {
                textFiledSize = it.size.toSize()
            },
        label = {
            Text(
                text = label,
                fontSize = 20.sp
            )
        },
        trailingIcon = {
            if (icon != null) {
                Icon(icon, "", Modifier.clickable {
                    isContextMenuVisible = !isContextMenuVisible
                })
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.Black,
            textColor = Color.Black,
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            disabledIndicatorColor = Color.Black,
            focusedLabelColor = Color.Red
        )
    )

    DropdownMenu(
        expanded = isContextMenuVisible,
        onDismissRequest = {
            isContextMenuVisible = false
        },
        modifier = modifier
            .width(with(LocalDensity.current) {
                textFiledSize.width.toDp()
            })
    ) {
        currencyList.forEach {
            DropdownMenuItem(onClick = {
                Log.d("TAG", "Selected item: $it")
                selectedItem = it.toString()
                isContextMenuVisible = false
                onValueChange(selectedItem)
            }) {

                Text(text = it.toString())
            }
        }
    }
}



