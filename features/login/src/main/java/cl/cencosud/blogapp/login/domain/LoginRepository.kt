package cl.cencosud.blogapp.login.domain

import com.google.firebase.auth.FirebaseUser

interface LoginRepository {
    suspend fun signIn(email:String,password:String): FirebaseUser?
}