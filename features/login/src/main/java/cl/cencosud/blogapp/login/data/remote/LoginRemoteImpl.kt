package cl.cencosud.blogapp.login.data.remote

import cl.cencosud.blogapp.login.data.remote.model.RemoteUser
import cl.cencosud.blogapp.login.data.source.LoginRemote
import cl.cencosud.blogapp.login.domain.model.LoginFirebaseException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class LoginRemoteImpl : LoginRemote {

    private val firebase by lazy { FirebaseAuth.getInstance() }

    override suspend fun signIn(email: String, password: String): RemoteUser =
        withContext(Dispatchers.IO) {
            val authResult = firebase.signInWithEmailAndPassword(email, password).await()
            val email = authResult.user?.email ?: throw LoginFirebaseException("Error to get the user email")
            val displayName = authResult.user?.displayName
            val photo = authResult.user?.photoUrl
            return@withContext RemoteUser(email =email, username = displayName)
        }
}