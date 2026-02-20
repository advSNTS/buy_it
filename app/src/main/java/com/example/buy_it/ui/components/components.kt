package com.example.buy_it.ui.components

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buy_it.R
import com.example.buy_it.ui.theme.Buy_itTheme
import kotlin.math.cos
import kotlin.math.sin

/*
se necesitan:
2 colores para el degradado, por color res
radio, siempre va a ser regular, en dp
el end offset, ahora es el angulo
y las posiciones en porcentajes

offset(x,y)
x = cos(angulo)
y = sin(angulo)
 */
@Composable
fun Elipse(
    colorStart: Color = MaterialTheme.colorScheme.primary, //anotacion de color res para recibir por color resource
    colorEnd: Color = MaterialTheme.colorScheme.onPrimary,
    radio: Dp = 50.dp,
    angulo: Float = 45f, //angulo del gradiente
    inicioGradiente: Float = 0.5f,
    finGradiente: Float = 0.0f,
    modifier: Modifier = Modifier
){
    //sacar los ids de los colores

    //tamaño del canva
    Canvas(modifier = modifier.size(radio * 2)){
        //canvas solo recibe px pero es bueno que el parametro sea dp para que en android se entienda la medida
        val radioPX = radio.toPx()
        //se tiene que convertir a radianes pq kotlin solo admite radianes
        val anguloRad = Math.toRadians(angulo.toDouble()).toFloat()

        // equivalente a offset de start
        val startX = center.x - cos(anguloRad) * radioPX
        val startY = center.y - sin(anguloRad) * radioPX
        // equivalente a offset de end
        val endX = center.x + cos(anguloRad) * radioPX
        val endY = center.y + sin(anguloRad) * radioPX

        //aca se dibuja
        drawCircle(
            brush = Brush.linearGradient(
                //desde donde van los colores
                colorStops = arrayOf(
                    inicioGradiente to colorStart,
                    finGradiente to colorEnd
                ),
                start = Offset(startX, startY), //el inicio con respecto al centro
                end = Offset(endX, endY)
            ),
            radius = radioPX,
            center = center
        )
    }
}

@Composable
@Preview()
fun ElipsePreview(){
    Buy_itTheme() {
        Elipse(
            radio = 100.dp,
            angulo = -71f,
            inicioGradiente = 0.1f,
            finGradiente = 0.7f

        )
    }

}

@Composable
fun CompleteELipse(
    @ColorRes colorStart: Int = R.color.graybluebuyit,
    modifier: Modifier = Modifier,
    sizeDraw: Dp = 10.dp,
){
    val color1 = colorResource(id = colorStart)
    Canvas(
        modifier = modifier.size(sizeDraw)
    ) {
        drawCircle(
            color = color1,
            radius = size.minDimension/2,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CompleteElipsePreview(){
    CompleteELipse(sizeDraw = 100.dp)
}


//circulo
//TODO: degradado, y que no sea imagen
@Composable
fun Circle(
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(R.drawable.elipse1),
) {
    Image(
        painter = painter,
        contentDescription = stringResource(R.string.imagen_de_elipse),
        modifier = modifier,
    )
}

@Composable
@Preview(showBackground = true)
fun CirclePreview(){
    Circle()
}



//Logo principal
//TODO: Degradado, definir color por resource
/*
Idea del degradado: debe estar a -58 grados (de figma)
con el primer color graybluebuyit en 17% y el segundo navybluebuyit en la posicion 100%
Offset(x,y) usa coordenadas cartesianas, por lo que
Posicion de x = cos(-58) = 0.5299
posicion de y = sen(-58) = -0.848
El degradado lo hace brush
 */
@Composable
fun LogoMessage(
    modifier: Modifier = Modifier,
    tamano: TextUnit = 96.sp,

){
    Text(
        text = "buy it.",

        style = TextStyle(
            fontSize = tamano,
            fontWeight = FontWeight(510),
            brush = Brush.linearGradient(
                colorStops = arrayOf(
                    0.17f to colorResource(R.color.graybluebuyit),
                    1f to colorResource(R.color.navybluebuyit)
                ),
                start = Offset(0f, 0f),
                end = Offset(0.5299f*1000f, -0.848f*100f) //x es el seno y y el cosenop
            ), //libreria de gradientes
            textAlign = TextAlign.Center
        ), modifier = modifier
    )
}

@Composable
@Preview(showBackground = true)
fun LogoMessagePreview(){
    LogoMessage()
}

//Imput de texto
//Recibe el texto como parámetro para ser reutilizado
@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    placeholder: String,
    item: String,
    onItemChange: (String) -> Unit,
){
    TextField(
        placeholder = {Text(placeholder)},
        modifier = modifier,
        value = item,
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.outline
        ),
        onValueChange = onItemChange,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
    )
}

@Composable
@Preview(showBackground = true)
fun TextInputPreview(){
    Buy_itTheme() {
        TextInput(item = "", placeholder = "Hyue", onItemChange = {})
    }


}

@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    placeholder: String,
    item: String,
    onItemChange: (String) -> Unit,
    mostrar: Boolean,
    onMostrarPassword: () -> Unit,
    icono: Int,
) {
    TextField(
        value = item,
        onValueChange = onItemChange,
        placeholder = { Text(placeholder) },
        visualTransformation = if (mostrar) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
        trailingIcon = {
            IconButton(onClick = onMostrarPassword) {
                Icon(
                    painter = painterResource(icono),
                    contentDescription = stringResource(R.string.mostar_contrase_a),
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    )
}
@Composable
@Preview(showBackground = true)
fun PasswordInputPreview(){
    PasswordInput(placeholder = "Holas", item = "", onItemChange = {}, mostrar = false, onMostrarPassword = {}, icono = R.drawable.see)
}

//Texto de recordar
//TODO: Volver a strinng resource
@Composable
fun CheckAndText(
    estado: Boolean = false,
    onEstadoChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(8.dp)
    ) {
        Checkbox(
            checked = estado,
            onCheckedChange = onEstadoChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.secondary,
                uncheckedColor = MaterialTheme.colorScheme.outline,
            )
        )
        Text(
            modifier = Modifier.padding(9.dp),
            text = "Recordarme",
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CheckAndTextPreview(){
    Buy_itTheme() {CheckAndText(estado = false, onEstadoChange = {}) }

}

//Boton Iniciar Sesión, el texto es pasado por parámetro
//A veces el color se bugea
@Composable
fun MainButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String
){
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onSecondaryContainer
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
    Buy_itTheme() { MainButton(text=stringResource(R.string.nombre), onClick = {})}


}

//Boton Registrarse, el texto es pasado por parámetro
@Composable
fun SecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
){
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
        )
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Composable
@Preview(showBackground = false)
fun SecondaryButtonPreview(){
    Buy_itTheme() {SecondaryButton(text = "Crear cuenta", onClick = {}) }

}


@Composable
fun ProfileText(
    modifier: Modifier= Modifier,
    text: String = "",
){
    Text(
        text=text,
        fontSize = 20.sp,
        fontWeight = FontWeight(500)
    )
}

@Composable
@Preview
fun ProfileTextPreview(){
    ProfileText(text="Prueba")
}

@Composable
fun ProfilePost(
    modifier: Modifier= Modifier,
    img: Int = R.drawable.cafe,
    descripcion: String,
){
    Image(
        painter = painterResource(img),
        contentDescription = descripcion,
        modifier = modifier.size(170.dp)
    )
}

@Composable
@Preview
fun ProfilePostPreview(){
    ProfilePost(img = R.drawable.cafe, descripcion = "Cafe")
}

@Composable
fun navbar(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
    ){
        Image(
            painter = painterResource(R.drawable.navbar),
            contentDescription = stringResource(R.string.navbar_background)
        )
        Row(

        ) {
            Image(
                painter = painterResource(R.drawable.home),
                contentDescription = stringResource(R.string.home_icon)
            )
            Image(
                painter = painterResource(R.drawable.plus),
                contentDescription = stringResource(R.string.home_icon)
            )
            Image(
                painter = painterResource(R.drawable.search),
                contentDescription = stringResource(R.string.home_icon)
            )
            Image(
                painter = painterResource(R.drawable.home),
                contentDescription = stringResource(R.string.home_icon)
            )
        }
    }
}

@Composable
@Preview
fun navbarPreview(){
    navbar()
}


/*
_________
Iconos
________

Por ahora el clic no hace nada
 */
@Composable
fun HomeIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(30.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Home,
            contentDescription = stringResource(R.string.inicio),
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun AddIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(30.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.agregar),
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun BuscarIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(30.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Buscar",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun ProfileIcon(
    @DrawableRes imageRes: Int = R.drawable.logo,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(30.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Perfil",
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}



@Composable
@Preview
fun IconsPreview(){
    Buy_itTheme() {
        Row() {
            HomeIcon()
            AddIcon()
            BuscarIcon()
            ProfileIcon()
        }
    }
}


@Composable
fun BarNav(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit = {},
    onBuscarClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {

    Row(
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(30.dp),
                clip = false
            )
            .clip(RoundedCornerShape(30.dp))
            .background(MaterialTheme.colorScheme.tertiary),

        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HomeIcon(onClick = onHomeClick)
        AddIcon(onClick = onAddClick)
        BuscarIcon(onClick = onBuscarClick)
        ProfileIcon(onClick = onProfileClick)
    }
}

@Composable
@Preview
fun navPreview(){
    Buy_itTheme() {
        BarNav()
    }
}
