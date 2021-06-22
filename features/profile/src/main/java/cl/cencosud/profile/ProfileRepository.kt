package cl.cencosud.profile

import android.graphics.Bitmap
import com.google.firebase.auth.FirebaseUser

interface ProfileRepository {
    suspend fun updateProfile(imageBitmap: Bitmap, username: String)
}