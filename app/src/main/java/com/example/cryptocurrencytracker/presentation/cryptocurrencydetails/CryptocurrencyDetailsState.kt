package com.example.cryptocurrencytracker.presentation.cryptocurrencydetails

import com.example.cryptocurrencytracker.domain.model.Cryptocurrency

data class CryptocurrencyDetailsState(
    val cryptocurrency: Cryptocurrency? = null
)
