package com.example.buy_it.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.buy_it.R


/*
Opciones de login, del mockup figma: google y apple
La imagen se le pasa por parámetro
TODO: Que el fondo no sea imagen sino un círculo
 */
@Composable
fun LoginOption(
    modifier: Modifier = Modifier,
    backgroundGlass: Painter,
    loginIcon: Painter,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = backgroundGlass,
            contentDescription = stringResource(R.string.fondo_de_una_forma_alterna_de_inicio_de_sesion),
        )
        Image(
            modifier = Modifier.fillMaxSize(0.7f),
            painter = loginIcon,
            contentDescription = stringResource(R.string.logo_de_forma_alterna_de_inicio_de_sesion)
        )
    }

}


@Composable
@Preview(showBackground = false)
fun LoginOptionPreview() {
    LoginOption(
        backgroundGlass = painterResource(R.drawable.elipse4),
        loginIcon = painterResource(R.drawable.googlewhite)
    )
}
