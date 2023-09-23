package com.itzik.currency.screens.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.itzik.currency.R

@Composable
fun GenericAutoCompleteTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier,
    currencyList: List<Pair<String, String>>,
    ) {
    var selectedItem by remember { mutableStateOf("") }
    val longCurrencyName = currencyList.map { it.second }
    var currencySuggestions by remember { mutableStateOf(longCurrencyName) }


    Column {
        TextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                currencySuggestions = longCurrencyName.filter { suggestion ->
                    suggestion.contains(it, ignoreCase = true)
                }
            }
        )

        LazyColumn {
            items(currencySuggestions) { suggestion ->
                Text(
                    text = suggestion,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedItem = suggestion.toString()
                            currencySuggestions = listOf()
                        }
                        .padding(8.dp)
                )
            }
        }
    }
}











