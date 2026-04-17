package com.example.cryptocurrencytracker.data.mappers

import com.example.cryptocurrencytracker.data.remote.dto.response.CryptocurrencyResponse
import com.example.cryptocurrencytracker.domain.model.Cryptocurrency

fun CryptocurrencyResponse.toDomain(): Cryptocurrency {
    return Cryptocurrency(
        symbol = symbol,
        priceChange = priceChange,
        priceChangePercent = priceChangePercent,
        weightedAvgPrice = weightedAvgPrice,
        prevClosePrice = prevClosePrice,
        lastPrice = lastPrice,
        lastQty = lastQty,
        bidPrice = bidPrice,
        bidQty = bidQty,
        askPrice = askPrice,
        askQty = askQty,
        openPrice = openPrice,
        highPrice = highPrice,
        lowPrice = lowPrice,
        volume = volume,
        quoteVolume = quoteVolume,
        openTime = openTime,
        closeTime = closeTime,
        firstId = firstId,
        lastId = lastId,
        count = count
    )
}