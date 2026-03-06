package com.example.buy_it.ui.screens.detail

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Detail(
    productId: String,
    onBackPressed: () -> Unit,
    onNotificationClick: () -> Unit,
    onOpenComments: () -> Unit,
    onSeeStores: () -> Unit,
    detailViewModel: DetailViewModel,
    modifier: Modifier = Modifier
) {
    val state by detailViewModel.uiState.collectAsState()

    // Cargar los datos cuando el productId cambie
    LaunchedEffect(productId) {
        detailViewModel.loadProductDetail(productId)
    }

    val product = state.product

    if (product == null) {
        // Podrías mostrar un loading o un error aquí si no se encuentra el producto
        return
    }

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
                    .padding(bottom = 86.dp),
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
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
                        onCommentClick = onOpenComments
                    )
                }
            }
        }

        ReviewInputBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp),
            text = "Reseñar...",
            onSend = { /* TODO: guardar reseña */ },
            onLike = { /* TODO */ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    Detail(
        productId = "rey_300g",
        onBackPressed = {},
        onNotificationClick = {},
        onOpenComments = {},
        onSeeStores = {},
        detailViewModel = DetailViewModel()
    )
}
