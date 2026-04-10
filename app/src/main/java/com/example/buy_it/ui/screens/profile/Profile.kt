package com.example.buy_it.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.buy_it.R
import com.example.buy_it.data.ProductInfo
import com.example.buy_it.ui.components.ProfileCircles
import com.example.buy_it.ui.screens.editinfo.PictureWithCircle
import com.example.buy_it.ui.screens.home.ProductCard
import com.example.buy_it.ui.theme.Buy_itTheme

@Composable
fun Profile(
    userId: String,
    onProfileEdit: () -> Unit,
    onConfigurationEdit: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit,
    onTrendsClick: () -> Unit,
    onOpenDetail: (String) -> Unit,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val state by profileViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        profileViewModel.getUserProfile(userId)
        profileViewModel.getUserReviews(userId)
    }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        ProfileCircles()

        Image(
            painter = painterResource(R.drawable.settings),
            contentDescription = stringResource(R.string.ajustes),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 26.dp, end = 20.dp)
                .size(28.dp)
                .clickable(onClick = onConfigurationEdit)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 90.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(30.dp))

                Box(contentAlignment = Alignment.BottomEnd) {
                    PictureWithCircle(profileLink = state.profileImage)

                    Card(
                        modifier = Modifier
                            .offset(x = 10.dp, y = (-4).dp)
                            .clickable(onClick = onProfileEdit),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.edit),
                                contentDescription = stringResource(R.string.editar_perfil),
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Editar perfil",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProfileStatItem(
                        value = state.memberSince,
                        label = "Miembro"
                    )
                    ProfileStatItem(
                        value = state.seguidoresCount,
                        label = stringResource(R.string.seguidores)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
            }

            items(state.reviews) { review ->
                ProductCard(
                    productInfo = ProductInfo(
                        id = review.productId,
                        name = review.product,
                        image = review.imgProd,
                        description = review.review,
                        likePercent = review.percentageLikes,
                        range = review.range,
                        ratingsCount = review.comments
                    ),
                    onClick = { onOpenDetail(review.productId) }
                )
            }
        }
    }
}

@Composable
private fun ProfileStatItem(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ProfilePreview() {
    Buy_itTheme {
        Profile(
            onProfileEdit = {},
            onConfigurationEdit = {},
            onHomeClick = {},
            onProfileClick = {},
            onTrendsClick = {},
            onOpenDetail = {},
            userId = "1",
        )
    }
}