package com.example.farmerpro.domain.repository

import com.example.farmerpro.domain.model.Resource
import com.example.farmerpro.types.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: FirebaseUser?
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(user: User, email: String, password: String): Flow<Resource<AuthResult>>

    fun signOut()
}
