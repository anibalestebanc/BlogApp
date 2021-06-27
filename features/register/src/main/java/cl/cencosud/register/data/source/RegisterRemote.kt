package cl.cencosud.register.data.source

import com.google.firebase.auth.FirebaseUser

interface RegisterRemote {
    suspend fun signUp(email: String, password: String, username: String): FirebaseUser?
}