package cl.cencosud.register.data.remote

import cl.cencosud.register.data.remote.model.DataNewUser
import cl.cencosud.register.data.source.RegisterRemote
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

const val USERS_COLLECTION: String = "users"

class RegisterRemoteImpl : RegisterRemote {

    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val firebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    override suspend fun signUp(email: String, password: String): String {
        return withContext(Dispatchers.IO) {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            authResult.user?.uid?.let { userId ->
                return@withContext userId
            }
            throw Exception("Error to register user")
        }
    }

    override suspend fun saveUser(userId: String, newUser: DataNewUser) {
        firebaseFirestore.collection(USERS_COLLECTION).document(userId).set(newUser)
    }
}