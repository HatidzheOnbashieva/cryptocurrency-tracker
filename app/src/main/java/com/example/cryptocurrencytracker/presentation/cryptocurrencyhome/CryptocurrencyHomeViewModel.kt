package com.example.cryptocurrencytracker.presentation.cryptocurrencyhome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencytracker.domain.usecase.CryptocurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptocurrencyHomeViewModel @Inject constructor(
    private val cryptocurrencyUseCase: CryptocurrencyUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CryptocurrencyHomeState())
    val state: StateFlow<CryptocurrencyHomeState> = _state.asStateFlow()

    init {
        getCryptocurrencies()
    }

    fun refresh() {
        getCryptocurrencies(isRefresh = true)
    }

    private fun getCryptocurrencies(isRefresh: Boolean = false) {
        viewModelScope.launch {
            _state.update {
                if (isRefresh) it.copy(isRefreshing = true)
                else it.copy(isLoading = true, error = null)
            }
            try {
                val result = cryptocurrencyUseCase.invoke()
                _state.update {
                    it.copy(
                        cryptocurrencies = result,
                        isLoading = false,
                        isRefreshing = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isRefreshing = false,
                        error = e.message
                    )
                }
            }
        }
    }
}