package cl.cencosud.register.data.remote

import cl.cencosud.blogapp.android.data.model.User
import cl.cencosud.register.data.source.RegisterRemote
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class RegisterRemoteImpl : RegisterRemote {

    override suspend fun signUp(email: String, password: String, username: String): FirebaseUser? {
        return withContext(Dispatchers.IO) {
            val authResult =
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
            authResult.user?.uid?.let { uid ->
                FirebaseFirestore.getInstance().collection("users").document(uid).set(
                    User(
                        email,
                        username,
                        "Photo_URL.png"
                    )
                )
            }
            authResult.user
        }
    }
}