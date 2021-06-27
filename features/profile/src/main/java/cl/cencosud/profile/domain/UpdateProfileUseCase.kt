package cl.cencosud.profile.domain

import android.graphics.Bitmap

class UpdateProfileUseCase(private val repository: ProfileRepository) {
    suspend fun invoke(imageBitmap: Bitmap, username: String) =
        repository.updateProfile(imageBitmap, username)
}