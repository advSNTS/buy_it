package com.example.buy_it.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Detail(
    productId: String,
    onBackPressed: () -> Unit,
    onNotificationClick: () -> Unit,
    onOpenComments: () -> Unit,
    onSeeStores: () -> Unit,
    onOpenReviewEditor: (String) -> Unit,
    onEditReview: (String, String) -> Unit,
    detailViewModel: DetailViewModel,
    modifier: Modifier = Modifier
) {
    val state by detailViewModel.uiState.collectAsState()

    LaunchedEffect(productId) {
        detailViewModel.loadProductDetail(productId)
    }

    if (state.isLoading) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            androidx.compose.material3.CircularProgressIndicator()
        }
        return
    }

    val product = state.product ?: return

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            DetailTopBar(
                title = "Ver detalle de producto",
                onBackPressed = onBackPressed,
                onNotificationClick = onNotificationClick
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item {
                    ProductHeaderCard(
                        name = product.name,
                        imageRes = product.image,
                        range = product.range,
                        likePercent = product.likePercent,
                        ratingsCount = product.ratingsCount,
                        onClickArrow = onSeeStores
                    )
                }

                item {
                    SectionTitle(text = "Opiniones del producto")
                }

                items(state.reviews) { review ->
                    ReviewMiniCard(
                        info = review,
                        onCommentClick = onOpenComments,
                        onClick = {
                            onEditReview(productId, review.id)
                        }
                    )
                }

                item {
                    androidx.compose.foundation.layout.Spacer(
                        modifier = Modifier.padding(bottom = 90.dp)
                    )
                }
            }
        }

        ReviewInputBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            text = "Reseñar...",
            onSend = { onOpenReviewEditor(productId) },
            onLike = { onOpenReviewEditor(productId) }
        )
    }
}