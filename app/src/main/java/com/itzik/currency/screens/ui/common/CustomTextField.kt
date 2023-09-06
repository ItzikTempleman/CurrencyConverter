package com.itzik.currency.screens.ui.common

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp


@Composable()
fun GenericTextField(
    input: String,
    onChange: (Any) -> Unit,
    label: String,
    modifier: Modifier,
    currencyList: List<Pair<String, String>>? = null,
    isKeyTypeNumOnly: Boolean,
) {
    var isExpanded by remember { mutableStateOf(false) }

    val icon = if (isExpanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    var inputValue =input
    //TODO EMPTY VALUE
    Log.d("TAG", "input: $inputValue")

    var selectedItem by remember { mutableStateOf("") }

    OutlinedTextField(
        keyboardOptions = if (isKeyTypeNumOnly) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        singleLine = true,
        textStyle = TextStyle(fontSize = 20.sp),
        value = inputValue,
        onValueChange = {
                inputValue = it
                //TODO NOT GETTING TO HERE
                Log.d("TAG", "input: $inputValue")
        },

        modifier = modifier.fillMaxWidth(),
        label = {
            Text(
                text = label,
                fontSize = 20.sp
            )
        },
        trailingIcon = {
            Icon(icon, "", Modifier.clickable {
                isExpanded = !isExpanded
            })
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
        expanded = isExpanded,
        onDismissRequest = {
            isExpanded = false
        }, modifier = modifier.fillMaxWidth()
    ) {
        currencyList?.forEach {
            DropdownMenuItem(onClick = {
                Log.d("WOW", "selected item is: $it")
                selectedItem = it.second
                isExpanded = false
            }) {
                Text(text = selectedItem)
            }
        }
    }
}


