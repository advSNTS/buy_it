package com.example.buy_it.ui.screens.trends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buy_it.data.repository.ReviewRepository
import com.example.buy_it.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import android.util.Log

@HiltViewModel
class TrendsViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val reviewRepository: ReviewRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TrendsState())
    val uiState: StateFlow<TrendsState> = _uiState

    init {
        loadFollowingReviews()
    }

    private fun loadFollowingReviews() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            val followingResult = userRepository.getFollowingIds()

            if (followingResult.isFailure) {
                Log.e("TrendsViewModel", "Error obteniendo seguidos: ${followingResult.exceptionOrNull()?.message}")
                _uiState.update { it.copy(isLoading = false) }
                return@launch
            }

            val followingIds = followingResult.getOrDefault(emptyList())

            if (followingIds.isEmpty()) {
                _uiState.update { it.copy(isLoading = false, reviews = emptyList(), filteredReviews = emptyList()) }
                return@launch
            }

            val reviewsResult = reviewRepository.getReviewsByUserIds(followingIds)

            if (reviewsResult.isSuccess) {
                val reviews = reviewsResult.getOrDefault(emptyList())
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        reviews = reviews,
                        filteredReviews = reviews
                    ) 
                }
            } else {
                Log.e("TrendsViewModel", "Error cargando reseñas: ${reviewsResult.exceptionOrNull()?.message}")
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun onSearchQueryChanged(newQuery: String) {
        _uiState.update { currentState ->
            val filtered = if (newQuery.isBlank()) {
                currentState.reviews
            } else {
                currentState.reviews.filter { 
                    it.product.contains(newQuery, ignoreCase = true) ||
                    it.name.contains(newQuery, ignoreCase = true)
                }
            }
            currentState.copy(
                searchQuery = newQuery,
                filteredReviews = filtered
            )
        }
    }
}