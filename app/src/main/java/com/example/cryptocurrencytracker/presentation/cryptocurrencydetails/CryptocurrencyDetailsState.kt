package com.example.cryptocurrencytracker.presentation.cryptocurrencydetails

import com.example.cryptocurrencytracker.presentation.cryptocurrencydetails.model.CryptocurrencyDetailsUi

data class CryptocurrencyDetailsState(
    val cryptocurrencyDetailsUiItems: List<CryptocurrencyDetailsUi> = emptyList()
)
