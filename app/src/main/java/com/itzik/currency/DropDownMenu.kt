package com.itzik.currency

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


import androidx.compose.ui.Modifier
import com.itzik.currency.constants.getCurrencyNames
import com.itzik.currency.viewmodels.CurrencyViewModel
import kotlinx.coroutines.CoroutineScope


@Composable
fun DropdownMenuBox(
    modifier: Modifier,
    currencyViewModel: CurrencyViewModel,
    coroutineScope: CoroutineScope,
) {
    var expanded by remember { mutableStateOf(false) }
    val nameList = getCurrencyNames
    var selectedItem by remember { mutableStateOf("") }
}