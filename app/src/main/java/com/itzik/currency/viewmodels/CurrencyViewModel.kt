package com.itzik.currency.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.itzik.currency.models.CurrencyResponse
import com.itzik.currency.repositories.CurrencyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel
@Inject constructor(
    private val currencyRepo: CurrencyRepo,
) : ViewModel() {

    suspend fun getCurrency(
        source: String,
        target: String,
        amount: Double,
    ): Flow<CurrencyResponse> {
        val viewModelFlow = flow {
            val response = currencyRepo.getCurrency(source, target, amount)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    emit(responseBody)
                } else Log.d("TAG", "failure message: " + response.message())
                return@flow
            } else Log.d("TAG", "failure message: " + response.message())
            return@flow
        }
        return viewModelFlow
    }

     fun shareInitialAndTargetCurrencyType(shortCurrencyName: String):String =shortCurrencyName

}