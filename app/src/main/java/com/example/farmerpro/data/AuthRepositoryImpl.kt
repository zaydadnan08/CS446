package com.example.farmerpro.data

import android.util.Log
import com.example.farmerpro.domain.model.Resource
import com.example.farmerpro.domain.model.Response
import com.example.farmerpro.domain.repository.AuthRepository
import com.example.farmerpro.types.User
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override val currentUser get() = firebaseAuth.currentUser
    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override fun registerUser(user: User, email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val db = Firebase.firestore

            val uid = result.user?.uid
            // Create a new user with a first and last name
            val user = hashMapOf(
                "name" to user.name,
                "userType" to user.type.toString(),
                "contact" to user.contactNumber,
                )

            if (uid != null) {
                db.collection("users")
                    .document(uid)
                    .set(user)
            }

            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override suspend fun getUserFromFirestore(uid: String): Response<User> {
        return try {
            val docSnapshot = Firebase.firestore.collection("users").document(uid).get().await()
            if (docSnapshot.exists()) {
                val userMap = docSnapshot.data
                if (userMap != null) {
                    val user = User(
                        userMap["name"] as String? ?: "",
                        userMap["userType"] as String? ?: "",
                        userMap["contact"] as String? ?: ""
                    )
                    Response.Success(user)
                } else {
                    Response.Failure(FirebaseException("Failed to convert document to User"))
                }
            } else {
                Response.Failure(FirebaseException("User document does not exist"))
            }
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override fun signOut() = firebaseAuth.signOut()
}