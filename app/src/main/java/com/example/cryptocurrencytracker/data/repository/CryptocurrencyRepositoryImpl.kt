package com.example.cryptocurrencytracker.data.repository

import com.example.cryptocurrencytracker.data.local.dao.CryptocurrencyDao
import com.example.cryptocurrencytracker.data.mappers.toDomain
import com.example.cryptocurrencytracker.data.mappers.toEntity
import com.example.cryptocurrencytracker.data.remote.api.CryptocurrencyApi
import com.example.cryptocurrencytracker.domain.model.Cryptocurrency
import com.example.cryptocurrencytracker.domain.repository.CryptocurrencyRepository
import javax.inject.Inject

class CryptocurrencyRepositoryImpl @Inject constructor(
    private val cryptocurrencyApi: CryptocurrencyApi,
    private val cryptocurrencyDao: CryptocurrencyDao
) : CryptocurrencyRepository {
    override suspend fun getCryptocurrencies(): List<Cryptocurrency> {
        return try {
            val response = cryptocurrencyApi.getCryptocurrencies()
            if (response.isSuccessful) {
                val list = response.body()?.map { it.toDomain() } ?: emptyList()
                cryptocurrencyDao.insertAll(list.map { it.toEntity() })
                list
            } else {
                cryptocurrencyDao.getAll().map { it.toDomain() }
            }
        } catch (_: Exception) {
            val cached = cryptocurrencyDao.getAll().map { it.toDomain() }
            if(cached.isEmpty()) throw Exception("No internet connection")
            cached
        }
    }

    override suspend fun getCryptocurrencyBySymbol(symbol: String): Cryptocurrency? {
        return cryptocurrencyDao.getBySymbol(symbol)?.toDomain()
    }
}