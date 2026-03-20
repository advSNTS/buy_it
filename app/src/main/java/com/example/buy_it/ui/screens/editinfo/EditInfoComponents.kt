package com.example.buy_it.ui.screens.editinfo

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.buy_it.R
import com.example.buy_it.ui.components.CompleteELipse
import com.example.buy_it.ui.components.ProfileAsyncImage

@Composable
fun PictureWithCircle(
    profileLink: String?,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ){
        CompleteELipse(
            sizeDraw = 175.dp
        )

        ProfileAsyncImage(
            profileLink = profileLink,
            size = 200,
        )

    }

}

@Composable
@Preview
fun PictureWithCirclePreview(){
    PictureWithCircle("")
}