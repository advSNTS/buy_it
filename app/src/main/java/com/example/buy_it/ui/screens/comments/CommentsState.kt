package com.example.buy_it.ui.screens.comments

import com.example.buy_it.data.CommentInfo

data class CommentsState(
    val comments: List<CommentInfo> = emptyList(),
    val newCommentText: String = "",
    val isLoading: Boolean = false
)