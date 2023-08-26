package com.itzik.currency.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
    var wantInitialValue by remember { mutableStateOf("0") }

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (chooseCurrencyDropDown, haveTF, wantTF, reverseTF) = createRefs()
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
                .constrainAs(haveTF) {
                    top.linkTo(chooseCurrencyDropDown.bottom)
                }
                .padding(12.dp)
                .fillMaxWidth(),
            value = haveInitialValue,
            onValueChange = {
                haveInitialValue = it
            },

            label = {
                Text(text = stringResource(id = R.string.i_have))
            },
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.Black,
                textColor = Color.Black,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Black,
                disabledIndicatorColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .constrainAs(wantTF) {
                    top.linkTo(haveTF.bottom)
                }
                .padding(12.dp)
                .fillMaxWidth(),
            value = wantInitialValue,
            onValueChange = {
                wantInitialValue = it
            },
            label = {
                Text(text = stringResource(id = R.string.i_want))
            },
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.Black,
                textColor = Color.Black,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Black,
                disabledIndicatorColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )
    }
}