package cl.cencosud.blogapp.login.data.source

import com.google.firebase.auth.FirebaseUser

interface LoginRemote {
    suspend fun signIn(email: String, password: String): FirebaseUser?
}