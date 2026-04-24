package com.example.buy_it.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buy_it.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.buy_it.data.ProductInfo
import com.example.buy_it.data.repository.UserRepository
import com.example.buy_it.data.repository.ReviewRepository

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val userRepository: UserRepository,
    private val reviewRepository: ReviewRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState

    fun getAllProducts() {
        viewModelScope.launch {
            productRepository.seedFirestoreProductsIfNeeded()

            val result = productRepository.getAllProducts()
            Log.d("Home", "All Prods: $result")

            if (result.isSuccess) {
                val products = result.getOrDefault(emptyList())

                _uiState.update {
                    it.copy(
                        products = products,
                        allProducts = products,
                        isFollowingFilterActive = false
                    )
                }
            }
        }
    }

    fun toggleFollowingFilter() {
        val isActive = _uiState.value.isFollowingFilterActive

        if (isActive) {
            _uiState.update {
                it.copy(
                    products = it.allProducts,
                    isFollowingFilterActive = false
                )
            }
            return
        }

        viewModelScope.launch {
            val followingResult = userRepository.getFollowingIds()

            if (followingResult.isFailure) {
                Log.e("Home", "Error obteniendo seguidos: ${followingResult.exceptionOrNull()?.message}")
                return@launch
            }

            val followingIds = followingResult.getOrDefault(emptyList())

            val reviewsResult = reviewRepository.getReviewsByUserIds(followingIds)

            if (reviewsResult.isSuccess) {
                val productsFromFollowingReviews = reviewsResult.getOrDefault(emptyList()).map { review ->
                    ProductInfo(
                        id = review.productId,
                        name = review.product,
                        image = review.imgProd,
                        description = review.review,
                        likePercent = review.percentageLikes,
                        range = review.range,
                        ratingsCount = review.comments
                    )
                }

                _uiState.update {
                    it.copy(
                        products = productsFromFollowingReviews,
                        isFollowingFilterActive = true
                    )
                }
            }
        }
    }

    init {
        getAllProducts()
    }
}