package com.itzik.currency.main


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.itzik.currency.navigation.SetupNavGraph
import com.itzik.currency.viewmodels.UnitViewModel
import com.itzik.theme.CurrencyTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var unitViewModel: UnitViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            unitViewModel = viewModel()
            val coroutineScope = rememberCoroutineScope()

            CurrencyTheme {
                SetupNavGraph(navHostController = rememberNavController(), unitViewModel= unitViewModel, coroutineScope=coroutineScope)
            }
        }
    }
}


