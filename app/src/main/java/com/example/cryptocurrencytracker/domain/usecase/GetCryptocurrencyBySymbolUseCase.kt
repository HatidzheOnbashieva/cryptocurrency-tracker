package com.example.cryptocurrencytracker.domain.usecase

import com.example.cryptocurrencytracker.domain.model.Cryptocurrency
import com.example.cryptocurrencytracker.domain.repository.CryptocurrencyRepository
import javax.inject.Inject

class GetCryptocurrencyBySymbolUseCase @Inject constructor(
    private val repository: CryptocurrencyRepository
) {
    suspend operator fun invoke(symbol: String): Cryptocurrency? =
        repository.getCryptocurrencyBySymbol(symbol)
}