package com.itzik.currency.screens.ui.common

import android.util.Log
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable()
fun GenericTextField(
    input: String,
    label: String,
    modifier: Modifier,
    currencyList: List<Pair<String, String>>,
    isKeyTypeNumOnly: Boolean,
) {
    val pattern = remember { Regex("^\\d+\$") }
    var isExpanded by remember { mutableStateOf(false) }
    var icon: ImageVector? = null
    if (!isKeyTypeNumOnly) {
        icon = if (isExpanded) {
            Icons.Filled.KeyboardArrowUp
        } else {
            Icons.Filled.KeyboardArrowDown
        }
    }

    var inputValue by remember {
        mutableStateOf(input)
    }
    Log.d("TAG", "'input' from parameter: $inputValue")

    var selectedItem by remember { mutableStateOf("") }

    OutlinedTextField(
        keyboardOptions = if (isKeyTypeNumOnly) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        singleLine = true,
        textStyle = TextStyle(fontSize = 20.sp),
        value = inputValue,
        onValueChange = {
            if(isKeyTypeNumOnly){
                if(it.matches(pattern))
                    inputValue = it
            } else inputValue = it
            Log.d("TAG", "'input' onValueChange: $inputValue")
        },

        modifier = modifier.fillMaxWidth(),
        label = {
            Text(
                text = label,
                fontSize = 20.sp
            )
        },
        trailingIcon = {
            if (icon != null) {
                Icon(icon, "", Modifier.clickable {
                    isExpanded = !isExpanded
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
        expanded = isExpanded,
        onDismissRequest = {
            isExpanded = false
        },
        modifier = Modifier.width(260.dp)
    ) {
        currencyList.forEach {
            DropdownMenuItem(onClick = {
                Log.d("TAG", "Selected item: $it")
                selectedItem = it.toString()
                isExpanded = false
            }) {
                Text(text = selectedItem)
            }
        }
    }
}



