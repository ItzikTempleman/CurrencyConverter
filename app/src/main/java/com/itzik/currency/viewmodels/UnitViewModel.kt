package com.itzik.currency.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.itzik.currency.models.CurrencyDropdownItemList
import com.itzik.currency.models.UnitResponse
import com.itzik.currency.repositories.CurrencyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class UnitViewModel
@Inject constructor(
    private val currencyRepo: CurrencyRepo,
) : ViewModel() {

    suspend fun getUnit(source: String, target: String, amount: Double): Flow<UnitResponse> {
        val unitFlow = flow {
            val response = currencyRepo.getUnit(source, target, amount)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    emit(responseBody)
                } else Log.d("TAG", "first failure message: " + response.message())
                return@flow
            } else Log.d("TAG", "second failure message: " + response.message())

            return@flow
        }
        return unitFlow
    }

    suspend fun getAllCurrenciesList(): Flow<CurrencyDropdownItemList> {
        val shortNameFlow = flow {
            val response = currencyRepo.getCurrencyList()
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    val responseBodyList = responseBody.result
                    emit(responseBodyList)
                } else Log.d("TAG", "first failure message: " + response.message())
                return@flow
            } else Log.d("TAG", "second failure message: " + response.message())

            return@flow
        }
        return shortNameFlow
    }
}