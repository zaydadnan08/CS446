package com.example.farmerpro.domain.repository

import android.content.Context
import com.example.farmerpro.domain.model.Resource
import com.example.farmerpro.domain.model.Response
import com.example.farmerpro.types.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

typealias UserResponse = Response<User>

interface AuthRepository {
    val currentUser: FirebaseUser?
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(user: User, email: String, password: String): Flow<Resource<AuthResult>>
    suspend fun getUserFromFirestore(uid: String): Response<User>
    fun signOut()
}
