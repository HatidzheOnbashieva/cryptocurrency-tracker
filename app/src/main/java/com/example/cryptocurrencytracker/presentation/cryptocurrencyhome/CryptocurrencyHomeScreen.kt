package com.example.cryptocurrencytracker.presentation.cryptocurrencyhome

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptocurrencytracker.domain.model.Cryptocurrency

@Composable
fun CryptocurrencyHomeScreen(
    viewModel: CryptocurrencyHomeViewModel = hiltViewModel(),
    onNavigateToDetails: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    PullToRefreshBox(
        isRefreshing = state.isRefreshing,
        onRefresh = { viewModel.refresh() },
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = state.error!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Home Screen",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily.SansSerif,
                            lineHeight = 22.sp
                        ),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()) {
                        items(state.cryptocurrencies, key = { it.symbol }) { cryptocurrency ->
                            CryptocurrencyItem(
                                cryptocurrency = cryptocurrency,
                                onClick = { onNavigateToDetails(cryptocurrency.symbol) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CryptocurrencyItem(
    cryptocurrency: Cryptocurrency,
    onClick: () -> Unit
) {
    val changePercent = cryptocurrency.priceChangePercent.toDoubleOrNull() ?: 0.0
    val changeColor = if (changePercent >= 0) Color(0xFF4CAF50) else Color(0xFFF44336)
    val formattedPercent = if (changePercent > 0) {
        "+${cryptocurrency.priceChangePercent}%"
    } else {
        "${cryptocurrency.priceChangePercent}%"
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = cryptocurrency.symbol,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = formattedPercent,
                color = changeColor,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "bid/ask: ${cryptocurrency.bidPrice}/${cryptocurrency.askPrice}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
    HorizontalDivider()
}