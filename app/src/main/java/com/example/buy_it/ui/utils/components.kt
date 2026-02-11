package com.example.buy_it.ui.utils

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buy_it.R

@Composable
fun Circle(
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(R.drawable.elipse1),
) {
    Image(
        painter = painter,
        contentDescription = "",
        modifier = modifier,
    )
}

@Composable
@Preview(showBackground = true)
fun CirclePreview(){
    Circle()
}

@Composable
fun GlassPanel(
    modifier : Modifier = Modifier
){
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
            TextInput(text= stringResource(R.string.e_mail))
            TextInput(text = stringResource(R.string.contrasenna))
            CheckAndText(modifier = Modifier.padding(top = 10.dp))
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

@Composable
fun LogoMessage(
    modifier: Modifier = Modifier,
    tamano: TextUnit = 96.sp,

){
    Text(
        text = "buy it.",
        modifier = modifier,
        style = TextStyle(
            fontSize = tamano,
            fontWeight = FontWeight(510),
            color = Color(0xFF596884),
            textAlign = TextAlign.Center
        )
    )
}

@Composable
@Preview(showBackground = true)
fun LogoMessagePreview(){
    LogoMessage()
}

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    text: String,
){
    TextField(
        modifier = modifier,
        value = text,
        textStyle = TextStyle(
            color = colorResource(R.color.graybuyit)
        ),
        onValueChange = {},
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
    )
}

@Composable
@Preview(showBackground = true)
fun TextInputPreview(){
    TextInput(text="Default")

}

@Composable
fun CheckAndText(
    modifier: Modifier = Modifier,
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(8.dp)
    ) {
        Checkbox(
            checked = true,
            onCheckedChange = {},
            colors = CheckboxDefaults.colors(
                checkedColor = colorResource(R.color.graybuyit),
                uncheckedColor = colorResource(R.color.graybuyit),
            )
        )
        Text(
            modifier = Modifier.padding(9.dp),
            text = "Recordarme",
            color = colorResource(R.color.graybuyit)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CheckAndTextPreview(){
    CheckAndText()
}

@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    text: String
){
    Button(
        onClick = {},
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.blackbuyit)
        )
    ) {
        Text(
            text = text
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MainButtonPreview(){
    MainButton(text="Iniciar Sesión")

}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
){
    Button(
        onClick = {},
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.white),
        )
    ) {
        Text(
            text = text,
            color = colorResource(R.color.blackbuyit)
        )
    }
}

@Composable
@Preview(showBackground = false)
fun SecondaryButtonPreview(){
    SecondaryButton(text = "Crear cuenta")
}


@Composable
fun LoginOption(
    modifier: Modifier = Modifier,
    backgroundGlass: Painter,
    loginIcon: Painter,
){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ){
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = backgroundGlass,
            contentDescription = "",
        )
        Image(
            modifier = Modifier.fillMaxSize(0.7f),
            painter = loginIcon,
            contentDescription = ""
        )
    }

}


@Composable
@Preview(showBackground = false)
fun LoginOptionPreview(){
    LoginOption(backgroundGlass = painterResource(R.drawable.elipse4), loginIcon = painterResource(R.drawable.googlewhite))
}