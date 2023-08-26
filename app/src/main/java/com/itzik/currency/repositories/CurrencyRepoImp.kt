package com.itzik.currency.repositories

import com.itzik.currency.models.CurrencyResponse
import com.itzik.currency.requests.CurrencyRequest
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class CurrencyRepoImp @Inject constructor(

    @Singleton
    private val currencyRequest: CurrencyRequest,
) : CurrencyRepo {
    override suspend fun getCurrency(
        sourceUnit: String,
        targetUnit: String,
        amount: Double,
    ): Response<CurrencyResponse> =
        currencyRequest.getCurrency(sourceUnit, targetUnit, amount)

}