package com.itzik.currency.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.currency.R
import com.itzik.currency.constants.getCurrencyNames
import com.itzik.currency.viewmodels.CurrencyViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun ConvertScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    currencyViewModel: CurrencyViewModel,
    coroutineScope: CoroutineScope,
) {
    var initialShortCurrencyName by remember { mutableStateOf("") }
    var initialFullCurrencyName by remember { mutableStateOf("") }
    var initialBothCurrencyNames by remember { mutableStateOf("") }
    var isInitialTFExpanded by remember { mutableStateOf(false) }

    var targetShortCurrencyName by remember { mutableStateOf("") }
    var targetFullCurrencyName by remember { mutableStateOf("") }
    var targetBothCurrencyNames by remember { mutableStateOf("") }
    var isTargetTFExpanded by remember { mutableStateOf(false) }

    var initialValue by remember { mutableStateOf("") }
    val targetValue by remember { mutableStateOf("") }
    var textFiledSize by remember { mutableStateOf(Size.Zero) }

    val list = getCurrencyNames

    val icon = if (isInitialTFExpanded || isTargetTFExpanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

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
            OutlinedTextField(
                textStyle = TextStyle(fontSize = 24.sp),
                value = initialFullCurrencyName,
                onValueChange = {
                    initialFullCurrencyName = it
                },
                modifier = Modifier

                    .padding(vertical = 2.dp, horizontal = 12.dp)
                    .fillMaxWidth()
                    .onGloballyPositioned {
                        textFiledSize = it.size.toSize()
                    },
                label = {
                    Text(
                        text = stringResource(id = R.string.initial_currency),
                        fontSize = 24.sp
                    )
                },
                trailingIcon = {
                    Icon(icon, "", Modifier.clickable {
                        isInitialTFExpanded = !isInitialTFExpanded
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
                expanded = isInitialTFExpanded,
                onDismissRequest = {
                    isInitialTFExpanded = false
                }, modifier = modifier
                    .width(with(LocalDensity.current) {
                        textFiledSize.width.toDp()
                    })
            ) {
                list.forEach {
                    DropdownMenuItem(onClick = {


                        initialFullCurrencyName = it.second
                        initialShortCurrencyName = it.first
                        Log.d("TAG", "initialShortCurrencyName $initialShortCurrencyName")
                        isInitialTFExpanded = false
                    }) {

                        initialBothCurrencyNames = it.toString()
                        Text(text = initialBothCurrencyNames)


                    }
                }
            }
        }

        Column(modifier = modifier.constrainAs(targetCurrencyTF) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(initialCurrencyTF.bottom)
        }) {
            OutlinedTextField(
                textStyle = TextStyle(fontSize = 24.sp),
                value = targetFullCurrencyName,
                onValueChange = {
                    targetFullCurrencyName = it
                },
                modifier = modifier
                    .padding(vertical = 2.dp, horizontal = 12.dp)
                    .fillMaxWidth()
                    .onGloballyPositioned {
                        textFiledSize = it.size.toSize()
                    },
                label = {
                    Text(
                        text = stringResource(id = R.string.target_currency),
                        fontSize = 24.sp
                    )
                },
                trailingIcon = {
                    Icon(icon, "", Modifier.clickable {
                        isTargetTFExpanded = !isTargetTFExpanded
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
                expanded = isTargetTFExpanded,
                onDismissRequest = {
                    isTargetTFExpanded = false
                }, modifier = modifier
                    .width(with(LocalDensity.current) {
                        textFiledSize.width.toDp()
                    })
            ) {
                list.forEach {
                    DropdownMenuItem(onClick = {

                        targetFullCurrencyName = it.second
                        targetShortCurrencyName = it.first
                        Log.d("TAG", "targetShortCurrencyName $targetShortCurrencyName")
                        isTargetTFExpanded = false
                    }) {

                        targetBothCurrencyNames = it.toString()
                        Text(text = targetBothCurrencyNames)
                    }
                }
            }
        }

        OutlinedTextField(
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .constrainAs(amountTF) {
                    top.linkTo(targetCurrencyTF.bottom)
                }
                .padding(horizontal = 12.dp, vertical = 24.dp)
                .fillMaxWidth(),
            value = initialValue,
            onValueChange = {
                initialValue = it
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
            ),
            textStyle = TextStyle(fontSize = 24.sp)
        )

        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(3.dp))
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(3.dp)
                )
                .background(colorResource(id = R.color.light_red))
                .constrainAs(valueText) {
                    top.linkTo(amountTF.bottom)
                    start.linkTo(amountTF.start)
                    end.linkTo(amountTF.end)
                }
                .width(390.dp)
                .padding(start = 24.dp, top = 8.dp, bottom = 8.dp)
                .height(40.dp),
            text = "Value: $targetValue",
            color = Color.White,
            fontSize = 24.sp
        )

        ReverseValues(
            modifier = Modifier
                .constrainAs(reverseIcon) {
                    top.linkTo(initialCurrencyTF.top)
                    end.linkTo(targetCurrencyTF.end)
                    bottom.linkTo(targetCurrencyTF.bottom)
                }
                .padding(end = 70.dp),
            currencyViewModel = currencyViewModel,
            coroutineScope = coroutineScope
        )
    }
}