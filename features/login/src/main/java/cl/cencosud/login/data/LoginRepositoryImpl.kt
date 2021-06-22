package cl.cencosud.login.data

import cl.cencosud.login.domain.LoginRepository
import com.google.firebase.auth.FirebaseUser

class LoginRepositoryImpl(private val cacheDataSource : CacheDataSource) : LoginRepository {
    override suspend fun signIn(email: String, password: String): FirebaseUser? =
        cacheDataSource.signIn(email, password)
}