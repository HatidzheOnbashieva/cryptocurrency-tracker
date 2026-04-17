package com.example.cryptocurrencytracker.presentation.cryptocurrencydetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencytracker.domain.usecase.GetCryptocurrencyBySymbolUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptocurrencyDetailsViewModel @Inject constructor(
    private val getBySymbol: GetCryptocurrencyBySymbolUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val symbol: String = checkNotNull(savedStateHandle["symbol"])
    private val _state = MutableStateFlow(CryptocurrencyDetailsState())
    val state: StateFlow<CryptocurrencyDetailsState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val cryptocurrency = getBySymbol(symbol)
            _state.update { it.copy(cryptocurrency = cryptocurrency) }
        }
    }
}