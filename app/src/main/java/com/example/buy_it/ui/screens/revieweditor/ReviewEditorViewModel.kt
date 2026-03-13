package com.example.buy_it.ui.screens.revieweditor

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ReviewEditorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ReviewEditorState())
    val uiState: StateFlow<ReviewEditorState> = _uiState.asStateFlow()

    fun onLikeChoiceChange(likeChoice: LikeChoice) {
        _uiState.update { it.copy(likeChoice = likeChoice) }
    }

    fun onOpinionChange(opinion: String) {
        _uiState.update { it.copy(opinion = opinion) }
    }

    fun buildDraft(productId: String): ReviewDraft {
        val state = _uiState.value
        return ReviewDraft(
            productId = productId,
            likeChoice = state.likeChoice,
            opinion = state.opinion.trim()
        )
    }
}