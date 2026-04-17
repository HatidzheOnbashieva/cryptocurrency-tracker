package com.example.cryptocurrencytracker.di

import android.content.Context
import androidx.room.Room
import com.example.cryptocurrencytracker.data.local.dao.CryptocurrencyDao
import com.example.cryptocurrencytracker.data.local.database.CryptocurrencyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CryptocurrencyDatabase =
        Room.databaseBuilder(context, CryptocurrencyDatabase::class.java, "cryptocurrency_db").build()

    @Provides @Singleton
    fun provideDao(db: CryptocurrencyDatabase): CryptocurrencyDao = db.cryptocurrencyDao()
}