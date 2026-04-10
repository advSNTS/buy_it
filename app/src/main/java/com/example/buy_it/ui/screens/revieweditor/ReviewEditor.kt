package com.example.buy_it.ui.screens.revieweditor

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.buy_it.data.datasource.local.ProductProvider
import com.example.buy_it.ui.components.MainBackground
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

@Composable
fun ReviewEditor(
    productId: String = "",
    reviewId: String? = null,
    onBackPressed: () -> Unit,
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ReviewEditorViewModel = hiltViewModel(),
) {
    val product = remember(productId) { ProductProvider.byId(productId) } ?: return
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(reviewId) {
        if (!reviewId.isNullOrBlank()) {
            viewModel.loadReviewForEdit(reviewId)
        }
    }

    LaunchedEffect(uiState.navigateBack) {
        if (uiState.navigateBack) {
            onBackPressed()
            viewModel.onNavigatedBack()
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        MainBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            ReviewEditorTopBar(
                title = if (uiState.isEditMode) "Editar reseña" else "Escribir reseña",
                onBackPressed = onBackPressed,
                onNotificationClick = onNotificationClick
            )

            ReviewEditorCard(
                username = "@tu",
                productName = product.name,
                productImage = product.image,
                likeChoice = uiState.likeChoice,
                onLikeChoiceChange = { viewModel.onLikeChoiceChange(it) },
                opinion = uiState.opinion,
                onOpinionChange = { viewModel.onOpinionChange(it) },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 10.dp)
            )

            PublishReviewButton(
                enabled = uiState.canPublish && !uiState.isLoading,
                text = if (uiState.isEditMode) "Guardar cambios" else "Publicar reseña",
                onClick = { viewModel.submitReview(productId) },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 18.dp)
            )

            uiState.errorMessage?.let { message ->
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            if (uiState.isEditMode) {
                Button(
                    onClick = { viewModel.deleteReview() },
                    enabled = !uiState.isLoading,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp, bottom = 90.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Eliminar reseña")
                }
            }

            uiState.errorMessage?.let { message ->
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}