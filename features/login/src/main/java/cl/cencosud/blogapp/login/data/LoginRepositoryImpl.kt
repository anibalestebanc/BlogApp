package cl.cencosud.blogapp.login.data

import cl.cencosud.blogapp.login.data.source.LoginRemote
import cl.cencosud.blogapp.login.domain.LoginRepository
import com.google.firebase.auth.FirebaseUser

class LoginRepositoryImpl(private val remote: LoginRemote) : LoginRepository {
    override suspend fun signIn(email: String, password: String): FirebaseUser? =
        remote.signIn(email, password)
}