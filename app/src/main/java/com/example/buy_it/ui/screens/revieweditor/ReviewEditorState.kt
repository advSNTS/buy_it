package com.example.buy_it.ui.screens.revieweditor

data class ReviewEditorState(
    val likeChoice: LikeChoice = LikeChoice.None,
    val opinion: String = "",
    val canPublish: Boolean = false,
)
