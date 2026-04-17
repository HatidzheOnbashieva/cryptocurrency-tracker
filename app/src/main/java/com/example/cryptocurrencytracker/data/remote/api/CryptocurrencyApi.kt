package com.example.cryptocurrencytracker.data.remote.api

import com.example.cryptocurrencytracker.data.remote.dto.response.CryptocurrencyResponse
import retrofit2.Response
import retrofit2.http.GET

interface CryptocurrencyApi {
    @GET("api/v3/ticker/24hr")
    suspend fun getCryptocurrencies(): Response<List<CryptocurrencyResponse>>
}