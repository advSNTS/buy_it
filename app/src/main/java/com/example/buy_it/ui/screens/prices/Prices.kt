package com.example.buy_it.ui.screens.prices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.buy_it.ui.components.GradientMessage

@Composable
fun Prices(
    pricesViewModel: PricesViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val state by pricesViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        GradientMessage("Precios", fontSize = 32.sp)

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(state.pricedItems) { item ->
                PriceCard(
                    info = item,
                    onClick = {}
                )
            }

            item {
                Spacer(modifier = Modifier.height(90.dp))
            }
        }
    }
}