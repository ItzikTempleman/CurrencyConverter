package com.itzik.currency


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.itzik.currency.constants.getCurrencyNames
import com.itzik.currency.viewmodels.CurrencyViewModel
import kotlinx.coroutines.CoroutineScope


@Composable
fun DropdownMenuBox(
    modifier: Modifier,
    currencyViewModel: CurrencyViewModel,
    coroutineScope: CoroutineScope,
    text: String,
) {
    var shortCurrencyName by remember { mutableStateOf("") }
    var fullCurrencyName by remember { mutableStateOf("") }
    var bothCurrencyNames by remember { mutableStateOf("") }


    var isExpanded by remember { mutableStateOf(false) }
    val list = getCurrencyNames
    var textFiledSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (isExpanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }
    Column(modifier = modifier) {
        OutlinedTextField(
            textStyle = TextStyle(fontSize = 24.sp),
            value = fullCurrencyName,
            onValueChange = {
                fullCurrencyName = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    textFiledSize = it.size.toSize()
                },
            label = {
                Text(
                    text = text,
                    fontSize = 24.sp
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
            }, modifier = Modifier
                .width(with(LocalDensity.current) {
                    textFiledSize.width.toDp()
                })
        ) {
            list.forEach {
                DropdownMenuItem(onClick = {
                    Log.d("TAG", "it $it")

                    fullCurrencyName = it.second
                    shortCurrencyName = it.first

                    isExpanded = false
                }) {

                    bothCurrencyNames = it.toString()
                    Text(text = bothCurrencyNames)

                    currencyViewModel.shareInitialAndTargetCurrencyType(shortCurrencyName)

                }
            }
        }

    }
}