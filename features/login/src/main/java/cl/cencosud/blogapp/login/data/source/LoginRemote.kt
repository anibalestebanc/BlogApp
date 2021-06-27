package cl.cencosud.blogapp.login.data.source
import cl.cencosud.blogapp.login.data.remote.model.RemoteUser

interface LoginRemote {
    suspend fun signIn(email: String, password: String): RemoteUser
}