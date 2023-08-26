package com.itzik.currency.requests

import com.itzik.currency.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyRequest {

    @GET("v1/convertcurrency?have={haveCurrency}&want={wantedCurrency}&amount={amount}")
    suspend fun getCurrency(
        @Path("haveCurrency") haveCurrency: String,
        @Path("wantedCurrency") wantedCurrency: String,
        @Path("amount") amount: Double,
    ): Response<CurrencyResponse>
}