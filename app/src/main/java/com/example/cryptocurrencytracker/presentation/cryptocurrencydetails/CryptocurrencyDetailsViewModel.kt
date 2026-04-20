package com.example.cryptocurrencytracker.presentation.cryptocurrencydetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencytracker.domain.model.Cryptocurrency
import com.example.cryptocurrencytracker.domain.usecase.GetCryptocurrencyBySymbolUseCase
import com.example.cryptocurrencytracker.presentation.cryptocurrencydetails.model.CryptocurrencyDetailsUi
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
            cryptocurrency?.let {
                _state.update {
                    it.copy(
                        cryptocurrencyDetailsUiItems = getCryptocurrencyDetailsItems(
                            cryptocurrency
                        )
                    )
                }
            }
        }
    }

    private fun getCryptocurrencyDetailsItems(cryptocurrency: Cryptocurrency): List<CryptocurrencyDetailsUi> =
        listOf(
            CryptocurrencyDetailsUi("Symbol", cryptocurrency.symbol),
            CryptocurrencyDetailsUi("Price change", cryptocurrency.priceChange),
            CryptocurrencyDetailsUi("Price change percent", cryptocurrency.priceChangePercent),
            CryptocurrencyDetailsUi("Weighted average price", cryptocurrency.weightedAvgPrice),
            CryptocurrencyDetailsUi("Previous close price", cryptocurrency.prevClosePrice),
            CryptocurrencyDetailsUi("Last price", cryptocurrency.lastPrice),
            CryptocurrencyDetailsUi("Last quantity", cryptocurrency.lastQty),
            CryptocurrencyDetailsUi("Bid price", cryptocurrency.bidPrice),
            CryptocurrencyDetailsUi("Bid quantity", cryptocurrency.bidQty),
            CryptocurrencyDetailsUi("Ask price", cryptocurrency.askPrice),
            CryptocurrencyDetailsUi("Ask quantity", cryptocurrency.askQty),
            CryptocurrencyDetailsUi("Open price", cryptocurrency.openPrice),
            CryptocurrencyDetailsUi("High price", cryptocurrency.highPrice),
            CryptocurrencyDetailsUi("Low price", cryptocurrency.lowPrice),
            CryptocurrencyDetailsUi("Volume", cryptocurrency.volume),
            CryptocurrencyDetailsUi("Quote volume", cryptocurrency.quoteVolume),
            CryptocurrencyDetailsUi("Open time", cryptocurrency.openTime.toString()),
            CryptocurrencyDetailsUi("Close time", cryptocurrency.closeTime.toString()),
            CryptocurrencyDetailsUi("First ID", cryptocurrency.firstId.toString()),
            CryptocurrencyDetailsUi("Last ID", cryptocurrency.lastId.toString()),
            CryptocurrencyDetailsUi("Count", cryptocurrency.count.toString()),
        )
}