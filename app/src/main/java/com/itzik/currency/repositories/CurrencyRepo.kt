package com.itzik.currency.repositories

import com.itzik.currency.models.CurrencyResponse
import retrofit2.Response

interface CurrencyRepo {

    suspend fun getCurrency(
        sourceUnit: String,
        targetUnit: String,
        amount: Double,
    ): Response<CurrencyResponse>

}