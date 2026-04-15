package com.example.buy_it.ui.screens.createreview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.buy_it.ui.components.TextInput
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import com.example.buy_it.ui.components.MainButton


@Composable
fun Createreview(
    onReviewSubmitted: () -> Unit,
    modifier: Modifier = Modifier,
    // viewModel: CreateReviewViewModel = hiltViewModel()
) {

    // val uiState by viewModel.uiState.collectAsState()
    val uiState = CreateReviewState()

    CreateReviewContent(
        uiState = uiState,
        onProductNameChange = { /* viewModel.onProductNameChange(it) */ },
        onLikeChange = { /* viewModel.onLikeChange(it) */ },
        onCommentChange = { /* viewModel.onCommentChange(it) */ },
        onSubmit = {
            // viewModel.submitReview()
            onReviewSubmitted()
        },
        modifier = modifier
    )
}

@Composable
private fun CreateReviewContent(
    uiState: CreateReviewState,
    onProductNameChange: (String) -> Unit,
    onLikeChange: (Boolean) -> Unit,
    onCommentChange: (String) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Nueva Reseña",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        // Campo: Nombre del Producto
        Column(modifier = Modifier.fillMaxWidth()) {
            FormFieldLabel(text = "Producto")
            TextInput(
                modifier = Modifier.fillMaxWidth(),
                placeholder = "Nombre del producto a reseñar",
                item = uiState.productName,
                onItemChange = onProductNameChange
            )
        }

        // Campo: Like (Booleano)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (uiState.isLiked) "¿Te gustó el producto?" else "¿No te gustó?",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            androidx.compose.material3.Switch(
                checked = uiState.isLiked,
                onCheckedChange = onLikeChange
            )
        }

        // Campo: Comentario
        Column(modifier = Modifier.fillMaxWidth()) {
            FormFieldLabel(text = "Tu opinión")
            TextInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                placeholder = "Escribe aquí lo que piensas del producto...",
                item = uiState.comment,
                onItemChange = onCommentChange
            )
        }

        if (uiState.errorMessage != null) {
            Text(
                text = uiState.errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        MainButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            text = "Publicar Reseña",
            onClick = onSubmit
        )
    }
}

@Composable
private fun FormFieldLabel(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier.fillMaxWidth(),
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onSurface
    )
}