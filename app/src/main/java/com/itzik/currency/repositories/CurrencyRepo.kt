package com.itzik.currency.repositories

import com.itzik.currency.models.UnitResponse
import retrofit2.Response

interface CurrencyRepo {

    suspend fun getUnit(sourceUnit: String, targetUnit: String, amount: Double): Response<UnitResponse>
}