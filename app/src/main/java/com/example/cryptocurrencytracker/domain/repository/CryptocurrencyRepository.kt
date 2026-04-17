package com.example.cryptocurrencytracker.domain.repository

import com.example.cryptocurrencytracker.domain.model.Cryptocurrency

interface CryptocurrencyRepository {
    suspend fun getCryptocurrencies(): List<Cryptocurrency>
    suspend fun getCryptocurrencyBySymbol(symbol: String): Cryptocurrency?
}