package com.itzik.currency.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.border
import com.itzik.currency.R

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.itzik.currency.viewmodels.CurrencyViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun ReverseValues(
    modifier: Modifier,
    currencyViewModel: CurrencyViewModel,
    coroutineScope: CoroutineScope
){
    FloatingActionButton(
        modifier = modifier.border( width = 2.dp,
            color = Color.Red,
            shape = RoundedCornerShape(80.dp)),
        onClick = {

        },
        backgroundColor = Color.White,
        shape = RoundedCornerShape(80.dp),
    ) {
        Icon(
            Icons.Filled.SwapVert,
            contentDescription = null,
            tint = Color.Red
        )
    }
}