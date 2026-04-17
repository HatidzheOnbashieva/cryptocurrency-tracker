package com.example.cryptocurrencytracker.di

import com.example.cryptocurrencytracker.data.repository.CryptocurrencyRepositoryImpl
import com.example.cryptocurrencytracker.domain.repository.CryptocurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCryptocurrencyRepository(
        cryptocurrencyRepositoryImpl: CryptocurrencyRepositoryImpl
    ): CryptocurrencyRepository
}