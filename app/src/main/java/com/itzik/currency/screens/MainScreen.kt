package com.itzik.currency.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.colorResource
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
            modifier = modifier
                .constrainAs(initialCurrencyTF) {
                    top.linkTo(logo.bottom)
                }
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            GenericTextField(
                value = stringToPairGetIndex(initialCurrencyName, 1),
                modifier = modifier,
                currencyList = currencyList,
                isKeyTypeNumOnly = false,
                label = stringResource(id = R.string.initial_currency),
                onValueChange = { initialCurrencyName = it }
            )
        }

        Column(
            modifier = modifier
                .constrainAs(targetCurrencyTF) {
                    top.linkTo(initialCurrencyTF.bottom)
                }
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            GenericTextField(
                value = stringToPairGetIndex(targetCurrencyName, 1),
                modifier = modifier,
                currencyList = currencyList,
                isKeyTypeNumOnly = false,
                label = stringResource(id = R.string.target_currency),
                onValueChange = { targetCurrencyName = it }
            )
        }

        GenericTextField(
            label = if (initialCurrencyName.isNotBlank()) stringResource(id = R.string.amount) + " of " + stringToPairGetIndex(
                initialCurrencyName,
                returnIndex = 1
            ) else stringResource(
                id = R.string.amount
            ),
            value = initialCurrencyAmount,
            modifier = modifier
                .constrainAs(amountTF) {
                    top.linkTo(targetCurrencyTF.bottom)
                }
                .fillMaxWidth()
                .padding(8.dp),
            currencyList = currencyList,
            isKeyTypeNumOnly = true,
            onValueChange = { initialCurrencyAmount = it }

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
        Box(
            modifier = modifier
                .constrainAs(valueText) {
                    top.linkTo(amountTF.bottom)
                }
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .clip(
                    RoundedCornerShape(32.dp)
                )
                .height(60.dp)
                .background(colorResource(id = R.color.standard_purple)),
        ) {


            Text(
                modifier = Modifier.padding(16.dp),

                text = "Value: $targetCurrencyAmount ${
                    stringToPairGetIndex(
                        targetCurrencyName,
                        returnIndex = 1
                    )
                }",
                color = Color.White,
                fontSize = 24.sp
            )
        }

        FloatingActionButton(
            modifier = Modifier
                .constrainAs(reverseIcon) {
                    top.linkTo(initialCurrencyTF.top)
                    end.linkTo(targetCurrencyTF.end)
                    bottom.linkTo(targetCurrencyTF.bottom)
                }

                .padding(top = 6.dp, end = 60.dp),

            onClick = {

            },
            backgroundColor = colorResource(id = R.color.standard_purple),
            shape = RoundedCornerShape(90.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.swapvert),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}