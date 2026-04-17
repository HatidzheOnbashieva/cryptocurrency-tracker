package com.example.cryptocurrencytracker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptocurrencytracker.data.local.entity.CryptocurrencyEntity

@Dao
interface CryptocurrencyDao {
    @Query("SELECT * FROM cryptocurrencies")
    suspend fun getAll(): List<CryptocurrencyEntity>

    @Query("SELECT * FROM cryptocurrencies WHERE symbol = :symbol LIMIT 1")
    suspend fun getBySymbol(symbol: String): CryptocurrencyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<CryptocurrencyEntity>)
}