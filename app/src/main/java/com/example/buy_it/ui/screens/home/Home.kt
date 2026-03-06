package com.example.buy_it.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.buy_it.ui.theme.Buy_itTheme

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
                contentPadding = PaddingValues(top = 8.dp)
            ) {
                items(state.reviews.size){ index ->
                    ReviewCard(
                        reviewInfo = state.reviews[index],
                        onClick = { onOpenDetail(state.reviews[index].productId) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePrev() {
    Buy_itTheme {
        Home(
            homeViewModel = viewModel(),
            onNotificationClick = {},
            onHomeClick = {},
            onProfileClick = {},
            onTrendsClick = {},
            onOpenDetail = {},
            onAddReview = {}
        )
    }
}
