package cl.cencosud.login

import com.google.firebase.auth.FirebaseUser

class LoginRepositoryImpl(private val cacheDataSource : CacheDataSource) : LoginRepository{
    override suspend fun signIn(email: String, password: String): FirebaseUser? =
        cacheDataSource.signIn(email, password)
}