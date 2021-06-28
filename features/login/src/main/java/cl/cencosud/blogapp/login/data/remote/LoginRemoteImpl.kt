package cl.cencosud.blogapp.login.data.remote

import cl.cencosud.blogapp.login.data.remote.model.RemoteUser
import cl.cencosud.blogapp.login.data.source.LoginRemote
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

const val USERS_COLLECTION : String = "users"
class LoginRemoteImpl : LoginRemote {

    private val firebase by lazy { FirebaseAuth.getInstance() }
    private val firebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    override suspend fun signIn(email: String, password: String): String =
        withContext(Dispatchers.IO) {
            val authResult = firebase.signInWithEmailAndPassword(email, password).await()
            authResult.user?.uid ?: throw Exception("Error to get user")
        }

    override suspend fun getUser(userId: String): RemoteUser? {
        val result = firebaseFirestore.collection(USERS_COLLECTION).document(userId).get().await()
        return result.toObject(RemoteUser::class.java)
    }
}