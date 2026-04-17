package com.example.cryptocurrencytracker.presentation.cryptocurrencydetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptocurrencytracker.R

@Composable
fun CryptocurrencyDetailsScreen(
    symbol: String,
    viewModel: CryptocurrencyDetailsViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
                    .clickable(
                        enabled = true,
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = onNavigateBack
                    ),
                tint = Color.Black,
                contentDescription = stringResource(id = R.string.back)
            )

            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "Details for $symbol",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    lineHeight = 22.sp
                ),
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }

        CryptocurrencyDetailsItem(label = "Symbol", value = state.cryptocurrency?.symbol ?: "")
        CryptocurrencyDetailsItem(
            label = "Price change",
            value = state.cryptocurrency?.priceChange ?: ""
        )
    }
}

@Composable
fun CryptocurrencyDetailsItem(
    label: String,
    value: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
    HorizontalDivider()
}