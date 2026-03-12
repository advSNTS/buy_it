package com.example.buy_it.ui.screens.comments

import androidx.lifecycle.ViewModel
import com.example.buy_it.data.local.CommentProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(CommentsState())
    val uiState: StateFlow<CommentsState> = _uiState

    fun loadComments(productId: String) {
        _uiState.update { it.copy(isLoading = true) }
        val productComments = CommentProvider.byProduct(productId)
        _uiState.update { it.copy(
            comments = productComments,
            isLoading = false
        ) }
    }

    fun onCommentTextChanged(newText: String) {
        _uiState.update { it.copy(newCommentText = newText) }
    }

    fun publishComment(productId: String) {
        val currentText = _uiState.value.newCommentText
        if (currentText.isNotBlank()) {
            // Aquí iría la lógica para guardar el comentario
            // Por ahora solo limpiamos el texto como ejemplo de acción del ViewModel
            _uiState.update { it.copy(newCommentText = "") }
            
            // Recargar para simular la actualización (aunque CommentProvider es estático)
            loadComments(productId)
        }
    }
}