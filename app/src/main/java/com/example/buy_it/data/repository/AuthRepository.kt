package com.example.buy_it.data.repository

import com.example.buy_it.data.datasource.AuthRemoteDataSource
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepository @Inject constructor (
    private val authRemoteDataSource: AuthRemoteDataSource
) {

    val currentUser: FirebaseUser? = authRemoteDataSource.currentUser

    suspend fun signIn(email: String, password: String): Result<Unit>{
        try {
            authRemoteDataSource.signInWithEmailAndPassword(email, password)
            return Result.success(Unit)
        }catch (e: Exception){
            return Result.failure(e)
        }
    }

    suspend fun signUp(email: String, password: String): Result<Unit>{
        try {
            authRemoteDataSource.signUpWithEmailAndPassword(email, password)
            return Result.success(Unit)
        }catch (e: Exception) {
            return Result.failure(e)
        }
    }

    fun signOut(){
        authRemoteDataSource.signOut()
    }



}