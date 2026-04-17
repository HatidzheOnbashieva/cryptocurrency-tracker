package com.example.cryptocurrencytracker.presentation.cryptocurrencyhome

import com.example.cryptocurrencytracker.domain.model.Cryptocurrency

data class CryptocurrencyHomeState(
    val cryptocurrencies: List<Cryptocurrency> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
)
