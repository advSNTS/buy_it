package com.example.buy_it.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buy_it.R
import com.example.buy_it.ui.utils.PictureWithCircle
import com.example.buy_it.ui.utils.ProfileCircles
import com.example.buy_it.ui.utils.ProfilePost
import com.example.buy_it.ui.utils.ProfileText

@Composable
fun Profile(
    modifier: Modifier = Modifier,
){
    Box(
        modifier = modifier
            .fillMaxSize(),

    ){
        ProfileCircles()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            PictureWithCircle(
                modifier = Modifier.offset(y=10.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfileText(text= stringResource(R.string._9))
                    ProfileText(text= stringResource(R.string.productos))
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfileText(text= stringResource(R.string._1_6k))
                    ProfileText(text= stringResource(R.string.seguidores))
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfileText(text= stringResource(R.string._128))
                    ProfileText(text= stringResource(R.string.seguidos))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                ProfilePost(img = R.drawable.cafe)
                ProfilePost(img = R.drawable.top)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                ProfilePost(img = R.drawable.rey)
                ProfilePost(img = R.drawable.mogolla)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                ProfilePost(img = R.drawable.carne)
                ProfilePost(img = R.drawable.toalla)
            }
        }
        Image(
            painter = painterResource(R.drawable.barra),
            contentDescription = stringResource(R.string.empty),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .size(50.dp)

        )
    }

}


@Composable
@Preview(showBackground = true)
fun ProfilePreview(){
    Profile()
}