package com.itzik.currency.requests

import com.itzik.currency.models.UnitResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyRequest {

    @GET("convert?from={sourceUnit}&to={targetUnit}&amount={amount}")
    suspend fun getTargetCurrencyFromSourceCurrency(
        @Path("sourceUnit") sourceUnit: String,
        @Path("targetUnit") targetUnit: String,
        @Path("amount") amount: Double,
    ): Response<UnitResponse>
}