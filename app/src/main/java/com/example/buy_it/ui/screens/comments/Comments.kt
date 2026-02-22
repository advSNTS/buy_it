package com.example.buy_it.ui.screens.comments

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buy_it.R
import com.example.buy_it.data.local.CommentsProvider

@Composable
fun Comments(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        val comments = CommentsProvider.CommentItems
        Image(
            painter = painterResource(R.drawable.arrowleft),
            contentDescription = stringResource(R.string.volver),
            modifier = Modifier
                .padding(start = 10.dp, top = 20.dp, bottom = 15.dp)
                .size(30.dp)
                .clickable(onClick = { onBackPressed() })
        )

        Text(
            text = stringResource(R.string.comentarios),
            style = TextStyle(
                fontSize = 45.sp,
                fontWeight = FontWeight(510),
                brush = Brush.linearGradient(
                    colorStops = arrayOf(
                        0.17f to colorResource(R.color.graybluebuyit),
                        1f to colorResource(R.color.navybluebuyit)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(0.5299f * 1000f, -0.848f * 100f) //x es el seno y y el cosenop
                ),
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(comments) { comment ->
                CommentCard(
                    info = comment,
                    onMoreClick = { },
                    onLikeClick = { }
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun CommentsPreview() {
    Comments({})
}
