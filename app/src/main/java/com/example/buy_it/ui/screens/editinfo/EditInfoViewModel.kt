package com.example.buy_it.ui.screens.editinfo

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buy_it.data.datasource.AuthRemoteDataSource
import com.example.buy_it.data.repository.AuthRepository
import com.example.buy_it.data.repository.StorageRepository
import com.example.buy_it.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class EditInfoViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val storageRepository: StorageRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        EditInfoState(
            email = authRepository.currentUser?.email ?: "",
            profileImage = authRepository.currentUser?.photoUrl?.toString()
        )
    )
    val uiState: StateFlow<EditInfoState> = _uiState.asStateFlow()

    init {
        loadCurrentUserProfile()
    }

    private fun loadCurrentUserProfile() {
        val currentUserId = authRepository.currentUser?.uid ?: return

        viewModelScope.launch {
            val result = userRepository.getUserById(currentUserId)

            if (result.isSuccess) {
                val user = result.getOrNull()
                if (user != null) {
                    _uiState.update {
                        it.copy(
                            name = user.name,
                            email = authRepository.currentUser?.email ?: user.email,
                            profileImage = authRepository.currentUser?.photoUrl?.toString()
                                ?: user.pfpURL
                        )
                    }
                }
            } else {
                _uiState.update {
                    it.copy(errormsg = result.exceptionOrNull()?.message)
                }
            }
        }
    }

    fun onNameChange(value: String) {
        _uiState.update { it.copy(name = value) }
    }

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun onToggleMostrarPassword() {
        _uiState.update { it.copy(mostrarPassword = !it.mostrarPassword) }
    }

    fun onSaveChanges(onSuccess: () -> Unit) {
        val state = _uiState.value
        val currentUserId = authRepository.currentUser?.uid

        if (currentUserId.isNullOrBlank()) {
            _uiState.update { it.copy(errormsg = "No hay un usuario autenticado") }
            return
        }

        if (state.name.isBlank()) {
            _uiState.update { it.copy(errormsg = "El nombre no puede estar vacío") }
            return
        }

        viewModelScope.launch {
            val result = userRepository.updateUserProfile(
                userId = currentUserId,
                name = state.name.trim(),
                pfpURL = state.profileImage
            )

            if (result.isSuccess) {
                if (!state.profileImage.isNullOrBlank()) {
                    authRemoteDataSource.updateProfileImage(state.profileImage)
                }
                _uiState.update { it.copy(errormsg = null) }
                onSuccess()
            } else {
                _uiState.update {
                    it.copy(errormsg = result.exceptionOrNull()?.message ?: "Error al guardar cambios")
                }
            }
        }
    }

    fun uploadImageToFirebase(uri: Uri) {
        viewModelScope.launch {
            val result = storageRepository.uploadProfileImage(uri)
            if (result.isSuccess) {
                _uiState.update { it.copy(profileImage = result.getOrNull()) }
            } else {
                _uiState.update { it.copy(errormsg = result.exceptionOrNull()?.message) }
            }
        }
    }
}