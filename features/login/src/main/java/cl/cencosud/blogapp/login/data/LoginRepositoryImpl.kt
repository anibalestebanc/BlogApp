package cl.cencosud.blogapp.login.data

import cl.cencosud.blogapp.login.data.data.source.RemoteDataSource
import cl.cencosud.blogapp.login.domain.LoginRepository
import com.google.firebase.auth.FirebaseUser

class LoginRepositoryImpl(private val remote: RemoteDataSource) : LoginRepository {
    override suspend fun signIn(email: String, password: String): FirebaseUser? =
        remote.signIn(email, password)
}