package com.example.buy_it.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buy_it.R
import com.example.buy_it.ui.components.CheckAndText
import com.example.buy_it.ui.components.FondoBlancoRegister
import com.example.buy_it.ui.components.MainButton
import com.example.buy_it.ui.components.PanelGlass
import com.example.buy_it.ui.components.PasswordInput
import com.example.buy_it.ui.components.TextInput
import com.example.buy_it.ui.theme.Buy_itTheme
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Register(
    registerButtonPressed: () -> Unit,
    onBackScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    // We pass the state and events to a stateless version of the Composable
    // to allow Previews to work without a ViewModel instance.
    RegisterContent(
        uiState = uiState,
        onUsernameChange = viewModel::onUsernameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
        onAcceptedTermsChange = { viewModel.onAcceptedTermsChange() },
        onToggleMostrarPassword = viewModel::onToggleMostrarPassword,
        onToggleMostrarConfirmPassword = viewModel::onToggleMostrarConfirmPassword,
        onRegister = viewModel::onRegister,
        onBackClicked = viewModel::onBackClicked,
        onNavigationHandled = viewModel::onNavigationHandled,
        registerButtonPressed = registerButtonPressed,
        onBackScreen = onBackScreen,
        modifier = modifier
    )
}

@Composable
fun RegisterContent(
    uiState: RegisterState,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onAcceptedTermsChange: (Boolean) -> Unit,
    onToggleMostrarPassword: () -> Unit,
    onToggleMostrarConfirmPassword: () -> Unit,
    onRegister: () -> Unit,
    onBackClicked: () -> Unit,
    onNavigationHandled: () -> Unit,
    registerButtonPressed: () -> Unit,
    onBackScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // Manejo de navegación mediante flags del UiState
    LaunchedEffect(uiState.navigateToHome) {
        if (uiState.navigateToHome) {
            registerButtonPressed()
            onNavigationHandled()
        }
    }

    LaunchedEffect(uiState.navigateBack) {
        if (uiState.navigateBack) {
            onBackScreen()
            onNavigationHandled()
        }
    }

    val iconoPassword = if (!uiState.mostrarPassword) R.drawable.hide else R.drawable.see
    val iconoConfirmPassword = if (!uiState.mostrarConfirmPassword) R.drawable.hide else R.drawable.see

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        FondoBlancoRegister()
        PanelGlass()
        Image(
            painter = painterResource(R.drawable.arrowleft),
            contentDescription = stringResource(R.string.volver),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 30.dp)
                .offset(y = 40.dp)
                .size(35.dp)
                .clickable { onBackClicked() }
        )
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 100.dp)
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
        ) {
            Text(
                modifier = Modifier.padding(5.dp),
                text = stringResource(R.string.crear_cuenta),
                fontSize = 46.sp,
                fontWeight = FontWeight(510),
                color = colorResource(R.color.navybluebuyit),
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(70.dp))
            TextInput(
                placeholder = stringResource(R.string.nombre_de_usuario),
                item = uiState.username,
                onItemChange = onUsernameChange
            )
            TextInput(
                placeholder = stringResource(R.string.email),
                item = uiState.email,
                onItemChange = onEmailChange
            )
            PasswordInput(
                placeholder = stringResource(R.string.contrasenna),
                item = uiState.password,
                onItemChange = onPasswordChange,
                icono = iconoPassword,
                mostrar = uiState.mostrarPassword,
                onMostrarPassword = onToggleMostrarPassword
            )
            PasswordInput(
                placeholder = stringResource(R.string.contrasenna),
                item = uiState.confirmPassword,
                onItemChange = onConfirmPasswordChange,
                icono = iconoConfirmPassword,
                mostrar = uiState.mostrarConfirmPassword,
                onMostrarPassword = onToggleMostrarConfirmPassword
            )
            Spacer(Modifier.height(20.dp))
            CheckAndText(
                estado = uiState.acceptedTerms,
                onEstadoChange = onAcceptedTermsChange,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.CenterHorizontally)
            )
            if (uiState.mostrarMensaje && uiState.errorMessage.isNotEmpty()) {
                Text(
                    text = uiState.errorMessage,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    color = colorResource(R.color.graybuyit)
                )
            }
            Spacer(Modifier.height(20.dp))
            MainButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
                text = stringResource(R.string.crear_cuenta),
                onClick = onRegister
            )
        }
    }
}

@Composable
@Preview(showBackground = false)
fun RegisterPreview() {
    Buy_itTheme {
        // We use the stateless RegisterContent for the preview to avoid ViewModel instantiation issues.
        RegisterContent(
            uiState = RegisterState(),
            onUsernameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onAcceptedTermsChange = {},
            onToggleMostrarPassword = {},
            onToggleMostrarConfirmPassword = {},
            onRegister = {},
            onBackClicked = {},
            onNavigationHandled = {},
            registerButtonPressed = {},
            onBackScreen = {}
        )
    }
}
