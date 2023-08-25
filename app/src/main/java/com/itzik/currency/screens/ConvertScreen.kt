package com.itzik.currency.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.itzik.currency.DropdownMenuBox
import com.itzik.currency.viewmodels.UnitViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun ConvertScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    unitViewModel: UnitViewModel,
    coroutineScope: CoroutineScope,
) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (dropDown) =createRefs()
        DropdownMenuBox(
            modifier=Modifier.constrainAs(dropDown){
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }.padding(12.dp)

        )
    }
}