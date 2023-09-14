package com.itzik.currency.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.itzik.currency.R
import com.itzik.currency.constants.getCurrencyNames
import com.itzik.currency.models.CurrencyResponse
import com.itzik.currency.screens.ui.common.GenericTextField
import com.itzik.currency.utils.stringToPairGetIndex
import com.itzik.currency.viewmodels.CurrencyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    modifier: Modifier,
    currencyViewModel: CurrencyViewModel,
    coroutineScope: CoroutineScope,
) {
    val currencyList = getCurrencyNames
    var currency: CurrencyResponse

    var initialCurrencyName by remember { mutableStateOf("") }
    var targetCurrencyName by remember { mutableStateOf("") }
    var initialCurrencyAmount by remember { mutableStateOf("") }
    var targetCurrencyAmount by remember { mutableStateOf("") }

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (logo, initialCurrencyTF, targetCurrencyTF, amountTF, valueText, reverseIcon) = createRefs()

        Image(
            modifier = Modifier
                .constrainAs(logo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(50.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = ""
        )

        Column(
            modifier = modifier.constrainAs(initialCurrencyTF) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(logo.bottom)
            }
        ) {
            GenericTextField(
                value = stringToPairGetIndex(initialCurrencyName , 1),
                modifier = modifier.padding(20.dp),
                currencyList = currencyList,
                isKeyTypeNumOnly = false,
                label = stringResource(id = R.string.initial_currency),
                onValueChange = { initialCurrencyName = it }
            )
        }

        Column(modifier = modifier.constrainAs(targetCurrencyTF) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(initialCurrencyTF.bottom)
        }
        ) {
            GenericTextField(
                value = stringToPairGetIndex(targetCurrencyName , 1),
                modifier = modifier.padding(20.dp),
                currencyList = currencyList,
                isKeyTypeNumOnly = false,
                label = stringResource(id = R.string.target_currency),
                onValueChange = { targetCurrencyName = it }
            )
        }

        GenericTextField(
            label = if (initialCurrencyName.isNotBlank()) stringResource(id = R.string.amount) + " of " + stringToPairGetIndex(initialCurrencyName, returnIndex = 1) else stringResource(
                id = R.string.amount
            ),
            value = initialCurrencyAmount,
            modifier = modifier
                .constrainAs(amountTF) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(targetCurrencyTF.bottom)
                }
                .padding(20.dp),
            currencyList = currencyList,
            isKeyTypeNumOnly = true,
            onValueChange = { initialCurrencyAmount = it }

        )

        Log.d(
            "TAG",
            "Status: ${initialCurrencyName.isNotBlank()} && ${targetCurrencyName.isNotBlank()} && ${initialCurrencyAmount.isNotBlank()}"
        )

        if (initialCurrencyName.isNotBlank() && targetCurrencyName.isNotBlank() && initialCurrencyAmount.isNotBlank()) {
            coroutineScope.launch {
                currencyViewModel.getCurrency(
                    stringToPairGetIndex(initialCurrencyName, 0),
                    stringToPairGetIndex(targetCurrencyName, 0),
                    initialCurrencyAmount.toDouble()
                ).collect {
                    currency = it
                    targetCurrencyAmount = if
                                                   (initialCurrencyName.isNotBlank()
                        && targetCurrencyName.isNotBlank()
                        && initialCurrencyName.isNotBlank()
                    )
                        currency.new_amount.toString() else ""
                }
            }
        }

        Text(
            modifier = modifier
                .constrainAs(valueText) {
                    top.linkTo(amountTF.bottom)
                    start.linkTo(amountTF.start)
                    end.linkTo(amountTF.end)
                }
                .padding(20.dp)
                .clip(RoundedCornerShape(4.dp))
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(4.dp)
                )
                .background(Color.Red)
                .fillMaxWidth()
                .padding(20.dp),
            text = "Value: $targetCurrencyAmount",
            color = Color.White,
            fontSize = 24.sp
        )

        FloatingActionButton(
            modifier = Modifier
                .constrainAs(reverseIcon) {
                    top.linkTo(initialCurrencyTF.top)
                    end.linkTo(targetCurrencyTF.end)
                    bottom.linkTo(targetCurrencyTF.bottom)
                }
                .padding(top = 6.dp, end = 50.dp)
                .border(
                    width = 2.dp,
                    color = Color.Red,
                    shape = RoundedCornerShape(90.dp)
                ),
            onClick = {

            },
            backgroundColor = Color.White,
            shape = RoundedCornerShape(90.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.swapvert),
                contentDescription = null,
                tint = Color.Red
            )
        }
    }
}