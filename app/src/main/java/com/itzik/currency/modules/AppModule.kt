package com.itzik.currency.modules

import com.itzik.currency.constants.Constants.API_HOST_NAME
import com.itzik.currency.constants.Constants.API_HOST_VALUE
import com.itzik.currency.constants.Constants.API_KEY_NAME
import com.itzik.currency.constants.Constants.API_KEY_VALUE
import com.itzik.currency.constants.Constants.BASE_URL
import com.itzik.currency.repositories.CurrencyRepo
import com.itzik.currency.repositories.CurrencyRepoImp
import com.itzik.currency.requests.CurrencyRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideCurrencyRepository(currencyRequest: CurrencyRequest): CurrencyRepo = CurrencyRepoImp(currencyRequest)


    @Provides
    @Singleton
    fun provideConvertService(): CurrencyRequest {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(UnitInterceptor())
                    .build()
            )
            .build()
        return retrofit.create(CurrencyRequest::class.java)
    }
}


class UnitInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request().newBuilder()
            .addHeader(API_KEY_NAME, API_KEY_VALUE)
            .addHeader(API_HOST_NAME, API_HOST_VALUE)
            .build()
        return chain.proceed(request)
    }
}

