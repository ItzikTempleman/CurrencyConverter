package com.itzik.currency.requests

import com.itzik.currency.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyRequest {

    @GET("v1/convertcurrency?have={haveCurrency}&want={wantedCurrency}&amount={amount}")
    suspend fun getCurrency(
        @Query("haveCurrency") haveCurrency: String,
        @Query("wantedCurrency") wantedCurrency: String,
        @Query("amount") amount: Double,
    ): Response<CurrencyResponse>
}