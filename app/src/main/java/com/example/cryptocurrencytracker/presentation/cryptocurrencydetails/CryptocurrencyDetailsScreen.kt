package com.example.cryptocurrencytracker.presentation.cryptocurrencydetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptocurrencytracker.R
import com.example.cryptocurrencytracker.presentation.cryptocurrencydetails.model.CryptocurrencyDetailsUi

@Composable
fun CryptocurrencyDetailsScreen(
    symbol: String,
    viewModel: CryptocurrencyDetailsViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        CryptocurrencyDetailsHeader(
            symbol = symbol,
            onNavigateBack = onNavigateBack
        )

        CryptocurrencyDetailsList(cryptocurrencyDetailsUiList = state.cryptocurrencyDetailsUiItems)
    }
}

@Composable
fun CryptocurrencyDetailsHeader(
    symbol: String,
    onNavigateBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(R.dimen.half_default_padding))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = dimensionResource(R.dimen.default_padding))
                .clickable(
                    enabled = true,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = onNavigateBack
                ),
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = stringResource(id = R.string.back)
        )

        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = stringResource(R.string.details_for, symbol),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                lineHeight = 22.sp
            ),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CryptocurrencyDetailsList(
    cryptocurrencyDetailsUiList: List<CryptocurrencyDetailsUi>
) {
    LazyColumn {
        itemsIndexed(cryptocurrencyDetailsUiList) { index, item ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(R.dimen.default_padding),
                        vertical = dimensionResource(R.dimen.half_default_padding)
                    )
            ) {
                Text(
                    text = item.label,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = item.value,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (index < cryptocurrencyDetailsUiList.lastIndex) {
                HorizontalDivider()
            }
        }
    }
}