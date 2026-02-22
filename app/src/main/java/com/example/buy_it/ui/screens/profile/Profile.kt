package com.example.buy_it.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buy_it.R
import com.example.buy_it.data.ProfileItems
import com.example.buy_it.data.local.ProfileItemsProvider
import com.example.buy_it.ui.components.BarNav
import com.example.buy_it.ui.components.ProfileCircles
import com.example.buy_it.ui.components.ProfilePost
import com.example.buy_it.ui.components.ProfileText
import com.example.buy_it.ui.screens.editinfo.PictureWithCircle
import com.example.buy_it.ui.theme.Buy_itTheme

@Composable
fun Profile(
    onProfileEdit: () -> Unit,
    onConfigurationEdit: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier,
){
    Box(
        modifier = modifier
            .fillMaxSize(),

    ){
        val allTweets = ProfileItemsProvider.itemsFromProfile
        ProfileCircles()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            PictureWithCircle()
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                items(allTweets.size) { index ->
                    ProfilePost(
                        img = allTweets[index].img,
                        descripcion = allTweets[index].descripcion
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

        }
        BarNav(
            modifier = Modifier
                .align(Alignment.BottomCenter) // Lo envía abajo al centro
                .padding(start = 8.dp, end = 8.dp), // Le da un margen para que "flote" y se vean las esquinas redondeadas
            onHomeClick = onHomeClick,
            onProfileClick = onProfileClick
            )

        Image(
            painter = painterResource(R.drawable.settings),
            contentDescription = stringResource(R.string.ajustes),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .fillMaxWidth()
                .size(30.dp)
                .offset(x = 150.dp, y = 30.dp)
                .clickable(
                    onClick = onConfigurationEdit
                )
        )
        Image(
            painter = painterResource(R.drawable.edit),
            contentDescription = stringResource(R.string.editar_perfil),

            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .size(40.dp)
                .offset(x = 100.dp, y = 140.dp)
                .clickable(
                    onClick = onProfileEdit
                )
        )

    }

}


@Composable
@Preview(showBackground = true)
fun ProfilePreview(){
    Buy_itTheme() { Profile({},{},{},{}) }
}
