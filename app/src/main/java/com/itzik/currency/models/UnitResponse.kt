package com.itzik.currency.models

import com.google.gson.annotations.SerializedName

data class UnitResponse(
    val result: Double,
    val request: Request,
    val success: Boolean,
    val meta: Meta
)

data class Request(
	val amount: Int,
	val from: String,
	val to: String
)

data class Format(
	val from: String,
	val to: String
)

data class Rates(
	val from: Double,
	val to: Double
)

data class Meta(
    val rates: Rates,
    @SerializedName("formated")
	val formattedRate: Format
)



