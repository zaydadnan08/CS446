package com.example.farmerpro.data

import com.example.farmerpro.types.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: FirebaseUser?
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(user: User, email: String, password: String): Flow<Resource<AuthResult>>

}
