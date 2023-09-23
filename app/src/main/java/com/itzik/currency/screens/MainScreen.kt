package com.itzik.currency.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.itzik.currency.R
import com.itzik.currency.constants.getCurrencyNames
import com.itzik.currency.models.CurrencyResponse
import com.itzik.currency.screens.ui.CustomImage
import com.itzik.currency.screens.ui.common.GenericCardText
import com.itzik.currency.screens.ui.common.GenericFloatingActionButton
import com.itzik.currency.screens.ui.common.GenericTextField
import com.itzik.currency.utils.isFieldsEmpty
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
    var currency = CurrencyResponse(0.0, "", "", 0.0)

    var initialCurrencyName by remember { mutableStateOf("") }
    var targetCurrencyName by remember { mutableStateOf("") }
    var initialCurrencyAmount by remember { mutableStateOf("") }
    var targetCurrencyAmount by remember { mutableStateOf("") }

    CustomImage()

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (logo, title, initialCurrencyTF, targetCurrencyTF, amountTF, reverseIcon, convertButton, valueShape) = createRefs()

        val (text, click, result) = createRefs()

        Image(modifier = Modifier
            .constrainAs(logo) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
            .padding(top = 12.dp, start = 16.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "")
        Text(modifier = modifier
            .constrainAs(title) {
                top.linkTo(logo.top)
                bottom.linkTo(logo.bottom)
                start.linkTo(logo.end)
                end.linkTo(parent.end)
            }
            .padding(top = 12.dp),

            style = TextStyle(
                fontWeight = FontWeight.Bold, fontSize = 26.sp, fontFamily = FontFamily.Monospace
            ),
            text = stringResource(id = R.string.title),
            color = colorResource(id = R.color.dark_blue_grey))

        Column(modifier = modifier
            .constrainAs(initialCurrencyTF) {
                top.linkTo(logo.bottom)
            }
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 100.dp)) {
            GenericTextField(
                value = stringToPairGetIndex(initialCurrencyName, 1),
                modifier = modifier,
                currencyList = currencyList,
                isKeyTypeNumOnly = false,
                label = stringResource(id = R.string.initial_currency),
                onValueChange = { initialCurrencyName = it }
            )

        }

        Column(modifier = modifier
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

        GenericFloatingActionButton(
            modifier = Modifier
                .zIndex(-100f)
                .constrainAs(reverseIcon) {
                    top.linkTo(targetCurrencyTF.bottom)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = 24.dp, vertical = 8.dp),
            onClick = {
                val temp = initialCurrencyName
                initialCurrencyName = targetCurrencyName
                targetCurrencyName = temp
            },
            backgroundColor = colorResource(id = R.color.orange),
            painter = painterResource(id = R.drawable.swapvert),
            contentDescription = null,
            tint = colorResource(id = R.color.white)
        )

        GenericTextField(
            label = if (initialCurrencyName.isNotBlank()) {
                stringResource(id = R.string.amount) + " " + stringToPairGetIndex(
                    initialCurrencyName,
                    returnIndex = 0
                ) + "s"
            } else stringResource(id = R.string.amount),
            value = initialCurrencyAmount,
            modifier = modifier
                .constrainAs(amountTF) {
                    top.linkTo(targetCurrencyTF.bottom)
                    start.linkTo(parent.start)
                }
                .wrapContentWidth()
                .width(270.dp)
                .padding(horizontal = 8.dp),
            currencyList = currencyList,
            isKeyTypeNumOnly = true,
            onValueChange = { initialCurrencyAmount = it }
        )

        GenericCardText(
            backgroundColor = colorResource(id = R.color.toolbar_blue),
            modifier = Modifier
                .constrainAs(convertButton) {
                    top.linkTo(amountTF.bottom)
                }
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp, top = 70.dp)
                .clip(
                    RoundedCornerShape(20.dp)
                )
                .wrapContentHeight(),
            constraintModifier = Modifier.clickable {
                if (isFieldsEmpty(
                        initialCurrencyName,
                        targetCurrencyName,
                        initialCurrencyAmount
                    )
                ) {

                    coroutineScope.launch {
                        currencyViewModel
                            .getCurrency(
                                stringToPairGetIndex(initialCurrencyName, 0),
                                stringToPairGetIndex(targetCurrencyName, 0),
                                initialCurrencyAmount.toDouble()
                            )
                            .collect {
                                currency = it
                                targetCurrencyAmount = if (isFieldsEmpty(
                                        initialCurrencyName,
                                        targetCurrencyName
                                    )
                                ) currency.new_amount.toString() else ""
                            }
                    }
                }
            },
            textModifier = Modifier
                .constrainAs(text) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .padding(16.dp),
            text = stringResource(id = R.string.convert),
            textColor = colorResource(id = R.color.white),
            fontSize = 20.sp,
            iconModifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(click) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            painter = painterResource(id = R.drawable.convert),
            contentDescription = null,
            tint = colorResource(id = R.color.white)

        )


        GenericCardText(
            backgroundColor = Color.White,
            modifier = Modifier
                .constrainAs(valueShape) {
                    top.linkTo(convertButton.bottom)
                }
                .fillMaxWidth()
                .padding(50.dp)
                .clip(
                    RoundedCornerShape(20.dp)
                )
                .wrapContentHeight(),
            constraintModifier = Modifier,
            textModifier = Modifier
                .constrainAs(result) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .padding(16.dp),
            text = if (isFieldsEmpty(
                    initialCurrencyName,
                    targetCurrencyName,
                    initialCurrencyAmount
                )
            ) {
                targetCurrencyAmount + " ${
                    stringToPairGetIndex(
                        targetCurrencyName,
                        returnIndex = 0
                    )
                }s"
            } else
                "No value",
            fontSize = 20.sp,
            textColor = Color.Black,
            iconModifier = null,
            painter = null,
            contentDescription = null,
            tint = null,
        )
    }
}






























/* convertData(
     coroutineScope =coroutineScope,
     currencyViewModel =currencyViewModel,
     currency =currency,
     initialCurrencyName =initialCurrencyName,
     targetCurrencyName =targetCurrencyName,
     initialCurrencyAmount =initialCurrencyAmount,
     targetCurrencyAmount =targetCurrencyAmount
 )
 */