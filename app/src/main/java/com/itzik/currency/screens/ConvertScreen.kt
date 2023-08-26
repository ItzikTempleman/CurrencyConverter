package com.itzik.currency.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.currency.DropdownMenuBox
import com.itzik.currency.R
import com.itzik.currency.viewmodels.CurrencyViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun ConvertScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    currencyViewModel: CurrencyViewModel,
    coroutineScope: CoroutineScope,
) {

    var haveInitialValue by remember { mutableStateOf("0") }
    var targetValue by remember { mutableStateOf("0") }
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (chooseCurrencyDropDown, amountTF, valueText, reverseIcon) = createRefs()
        DropdownMenuBox(
            modifier = Modifier
                .constrainAs(chooseCurrencyDropDown) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .padding(12.dp),
            currencyViewModel = currencyViewModel,
            coroutineScope = coroutineScope
        )



        OutlinedTextField(
            modifier = Modifier
                .constrainAs(amountTF) {
                    top.linkTo(chooseCurrencyDropDown.bottom)
                }
                .padding(12.dp)
                .fillMaxWidth(),
            value = haveInitialValue,
            onValueChange = {
                haveInitialValue = it
            },

            label = {
                Text(
                    text = stringResource(id = R.string.amount),
                    fontSize = 24.sp
                )
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

        Text(
            modifier = Modifier.clip(RoundedCornerShape(4.dp)).border( width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(4.dp))
                .background(colorResource(id = R.color.light_red))
                .constrainAs(valueText) {
                    top.linkTo(amountTF.bottom)
                }.fillMaxWidth()
                .padding(12.dp)
                .height(35.dp),
            text = "Value: $targetValue",
            color = Color.White,
            fontSize = 24.sp
        )

        ReverseValues(
            modifier = Modifier
                .constrainAs(reverseIcon) {
                    top.linkTo(amountTF.top)
                    end.linkTo(amountTF.end)
                    bottom.linkTo(valueText.bottom)
                }
                .padding(end = 30.dp),
            currencyViewModel = currencyViewModel,
            coroutineScope = coroutineScope
        )
    }
}