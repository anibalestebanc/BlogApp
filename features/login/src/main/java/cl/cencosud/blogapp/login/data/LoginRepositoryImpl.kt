package cl.cencosud.blogapp.login.data

import cl.cencosud.blogapp.login.domain.LoginRepository
import com.google.firebase.auth.FirebaseUser

class LoginRepositoryImpl(private val remoteDataSource : RemoteDataSource) : LoginRepository {
    override suspend fun signIn(email: String, password: String): FirebaseUser? =
        remoteDataSource.signIn(email, password)
}