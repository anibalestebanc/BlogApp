package cl.cencosud.register.data

import cl.cencosud.register.data.source.RegisterRemote
import cl.cencosud.register.domain.RegisterRepository
import com.google.firebase.auth.FirebaseUser

class RegisterRepositoryImpl(private val remote: RegisterRemote) : RegisterRepository {
    override suspend fun signUp(email: String, password: String, username: String): FirebaseUser? =
        remote.signUp(email, password, username)
}