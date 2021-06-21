package cl.cencosud.register

import com.google.firebase.auth.FirebaseUser

class RegisterRepositoryImpl(private val remoteDataSource: RemoteDataSource) : RegisterRepository {
    override suspend fun signUp(email: String, password: String, username: String): FirebaseUser? =
        remoteDataSource.signUp(email, password, username)
}