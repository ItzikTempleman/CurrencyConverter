package com.itzik.currency.utils

import androidx.compose.runtime.MutableState
import com.itzik.currency.models.CurrencyResponse
import com.itzik.currency.viewmodels.CurrencyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun convertData(
    coroutineScope: CoroutineScope,
    currencyViewModel: CurrencyViewModel,
    currency: CurrencyResponse,
    initialCurrencyName: String,
    targetCurrencyName: String,
    initialCurrencyAmount:String,
    targetCurrencyAmount:String
    ):CurrencyResponse {
    var _currency=currency
    var updatedTargetCurrencyAmount=targetCurrencyAmount
    coroutineScope.launch {
        currencyViewModel
            .getCurrency(
                stringToPairGetIndex(initialCurrencyName, 0),
                stringToPairGetIndex(targetCurrencyName, 0),
                initialCurrencyAmount.toDouble()
            )
            .collect {
                _currency = it
                updatedTargetCurrencyAmount = if (isFieldsEmpty(
                        initialCurrencyName,
                        targetCurrencyName
                    )
                ) currency.new_amount.toString() else ""
            }
    }
    return _currency
}