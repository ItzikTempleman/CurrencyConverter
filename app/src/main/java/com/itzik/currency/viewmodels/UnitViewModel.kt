package com.itzik.currency.viewmodels

import androidx.lifecycle.ViewModel
import com.itzik.currency.models.UnitResponse
import com.itzik.currency.repositories.CurrencyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UnitViewModel
@Inject constructor(
    private val currencyRepo: CurrencyRepo,
) : ViewModel() {

    suspend fun getAllNotes(
        source: String,
        target: String,
        amount: Double,
    ): Flow<Response<UnitResponse>> {
        val unit = flow {
            val updatedFlow = currencyRepo.getUnit(source, target, amount)
            emit(updatedFlow)
        }
        return unit
    }
}