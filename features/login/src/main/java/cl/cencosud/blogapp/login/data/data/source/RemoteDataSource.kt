package cl.cencosud.blogapp.login.data.data.source

import com.google.firebase.auth.FirebaseUser

interface RemoteDataSource {
    suspend fun signIn(email: String, password: String): FirebaseUser?
}