package com.example.buy_it.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Home(
    homeViewModel: HomeViewModel,
    onNotificationClick: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit,
    onTrendsClick: () -> Unit,
    onOpenDetail: (String) -> Unit,
    onAddReview: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val state by homeViewModel.uiState.collectAsState()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 12.dp,
                    end = 12.dp,
                    top = 8.dp,
                    bottom = 90.dp
                )
            ) {
                items(state.products) { product ->
                    ProductCard(
                        productInfo = product,
                        onClick = { onOpenDetail(product.id) }
                    )
                }
            }
        }
    }
}