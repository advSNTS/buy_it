package com.example.buy_it.ui.screens.trends

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buy_it.ui.components.MainBackground
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.text.font.FontWeight
import com.example.buy_it.data.ProductInfo
import com.example.buy_it.ui.screens.home.ProductCard
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Trends(
    onOpenDetail: (String) -> Unit,
    trendsViewModel: TrendsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state by trendsViewModel.uiState.collectAsState()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        MainBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            Text(
                text = "Actividad de seguidos",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            SearchBar(
                value = state.searchQuery,
                onValueChange = { trendsViewModel.onSearchQueryChanged(it) }
            )

            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (state.filteredReviews.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (state.searchQuery.isEmpty())
                            "No hay actividad reciente de tus seguidos."
                        else "No se encontraron reseñas.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = 16.dp, bottom = 90.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(state.filteredReviews) { review ->
                        ProductCard(
                            productInfo = ProductInfo(
                                id = review.productId,
                                name = review.product,
                                image = review.imgProd,
                                description = review.review,
                                likePercent = review.percentageLikes,
                                range = review.range,
                                ratingsCount = review.likesCount
                            ),
                            onClick = { onOpenDetail(review.productId) },
                            isLikeCount = true
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                "Buscar por producto o usuario",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        singleLine = true,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Buscar",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        shape = RoundedCornerShape(18.dp),
        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedBorderColor = MaterialTheme.colorScheme.outline,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
            cursorColor = MaterialTheme.colorScheme.primary
        )
    )
}
