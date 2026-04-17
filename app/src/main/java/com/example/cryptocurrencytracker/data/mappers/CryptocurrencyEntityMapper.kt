package com.example.cryptocurrencytracker.data.mappers

import com.example.cryptocurrencytracker.data.local.entity.CryptocurrencyEntity
import com.example.cryptocurrencytracker.domain.model.Cryptocurrency

fun CryptocurrencyEntity.toDomain(): Cryptocurrency {
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
        count = count,
    )
}

fun Cryptocurrency.toEntity(): CryptocurrencyEntity {
    return CryptocurrencyEntity(
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