package com.example.cryptocurrencytracker.domain.usecase

import com.example.cryptocurrencytracker.domain.model.Cryptocurrency
import com.example.cryptocurrencytracker.domain.repository.CryptocurrencyRepository
import javax.inject.Inject

class CryptocurrencyUseCase @Inject constructor(
    val cryptocurrencyRepository: CryptocurrencyRepository
) {
    suspend operator fun invoke(): List<Cryptocurrency> {
        return cryptocurrencyRepository.getCryptocurrencies()
    }
}