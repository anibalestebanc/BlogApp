package cl.cencosud.register.domain

import com.google.firebase.auth.FirebaseUser

interface RegisterRepository {
    suspend fun signUp(email: String, password: String, username: String): FirebaseUser?
}