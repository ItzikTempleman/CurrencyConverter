package com.itzik.currency.repositories

import com.itzik.currency.models.CurrencyDropdownItemResponse
import com.itzik.currency.models.UnitResponse
import com.itzik.currency.requests.CurrencyRequest
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class CurrencyRepoImp @Inject constructor(

    @Singleton
   private val currencyRequest: CurrencyRequest
) : CurrencyRepo {
    override suspend fun getUnit(sourceUnit: String, targetUnit: String, amount: Double): Response<UnitResponse> = currencyRequest.getTargetCurrencyFromSourceCurrency(sourceUnit,  targetUnit, amount)
    override suspend fun getCurrencyList(): Response<CurrencyDropdownItemResponse> =currencyRequest.getCurrencyList()
}