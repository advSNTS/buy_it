package com.example.buy_it.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buy_it.R
import com.example.buy_it.ui.components.CheckAndText
import com.example.buy_it.ui.components.LogoMessage
import com.example.buy_it.ui.components.MainButton
import com.example.buy_it.ui.components.PasswordInput
import com.example.buy_it.ui.components.SecondaryButton
import com.example.buy_it.ui.components.TextInput

//panel principal
/*
Incluye los composables :
Botones de login, otras formas, y checkbox
Campos de texto
Logo
 */
//TODO: volverlo vidrio
@Composable
fun GlassPanel(
    modifier : Modifier = Modifier
){
    var email by remember { mutableStateOf("") }
    var password by remember {mutableStateOf("")}
    var selected by remember {mutableStateOf(false)}
    var mostrar by remember {mutableStateOf(false)}
    var icono = if(mostrar) R.drawable.see else R.drawable.hide


    Box(
        modifier = modifier
            .width(352.dp)
            .height(800.dp)
            .background(
                color = colorResource(R.color.glasswhite),
                shape = RoundedCornerShape(size = 25.dp)
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoMessage(modifier = Modifier.padding(top = 35.dp, bottom = 40.dp))
            TextInput(placeholder = "Email", item = email, onItemChange = {email = it})
            PasswordInput(placeholder = "Contraseña", item = password, onItemChange = {password = it}, mostrar = mostrar, onMostrarPassword = {mostrar = !mostrar}, icono = icono)
            CheckAndText(estado = selected, onEstadoChange = {selected = !selected},modifier = Modifier.padding(top = 10.dp))
            Text(
                text = stringResource(R.string.olvido_su_contrasenna),
                textDecoration = TextDecoration.Underline,
            )
            MainButton(modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),text = stringResource(R.string.iniciar_sesion)
            )
            SecondaryButton(modifier = Modifier
                .padding(bottom = 40.dp)
                .fillMaxWidth(),text = stringResource(R.string.crear_cuenta)
            )
            Text(modifier = Modifier.padding(bottom = 20.dp),text = stringResource(R.string.otras_formas_de_iniciar_sesion), fontWeight = FontWeight(510), fontSize = 16.sp)
            Row() {
                //Cambiar esto cuando se cambie el fondo de ciruculo de imagen a figura
                LoginOption(Modifier.size(74.dp),backgroundGlass = painterResource(R.drawable.elipse4), loginIcon = painterResource(R.drawable.googlewhite))
                Spacer(Modifier.width(30.dp))
                LoginOption(Modifier.size(74.dp),backgroundGlass = painterResource(R.drawable.elipse5), loginIcon = painterResource(R.drawable.apple))
            }
        }
    }
}

@Composable
@Preview(showBackground = false)
fun GlassPanelPreview(){
    GlassPanel()
}

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
