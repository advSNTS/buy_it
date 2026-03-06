package com.example.buy_it.ui.screens.editinfo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.buy_it.R
import com.example.buy_it.ui.components.FondoBlancoEditInfo
import com.example.buy_it.ui.components.MainButton
import com.example.buy_it.ui.components.PanelGlass
import com.example.buy_it.ui.components.PasswordInput
import com.example.buy_it.ui.components.TextInput
import com.example.buy_it.ui.theme.Buy_itTheme

@Composable
fun EditInfo(
    onSaveChanges: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditInfoViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    val icono = if (!uiState.mostrarPassword) R.drawable.hide else R.drawable.see

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        FondoBlancoEditInfo()
        PanelGlass()
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 100.dp)
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                PictureWithCircle()
            }
            Spacer(Modifier.height(30.dp))
            Text(
                text = stringResource(R.string.nombre),
            )
            TextInput(
                placeholder = stringResource(R.string.buy_it),
                item = uiState.name,
                onItemChange = { viewModel.onNameChange(it) }
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.email),
            )
            TextInput(
                placeholder = stringResource(R.string.buyit_buyit_com),
                item = uiState.email,
                onItemChange = { viewModel.onEmailChange(it) }
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.contrasenna),
            )
            PasswordInput(
                placeholder = stringResource(R.string.contrasenna),
                item = uiState.password,
                onItemChange = { viewModel.onPasswordChange(it) },
                icono = icono,
                mostrar = uiState.mostrarPassword,
                onMostrarPassword = { viewModel.onToggleMostrarPassword() }
            )
            Spacer(Modifier.height(60.dp))
            MainButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.guardar_cambios),
                onClick = {
                    viewModel.onSaveChanges()
                    onSaveChanges()
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun EditInfoPreview() {
    Buy_itTheme { EditInfo({}) }
}