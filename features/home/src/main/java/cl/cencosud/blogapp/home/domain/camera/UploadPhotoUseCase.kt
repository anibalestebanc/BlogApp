package cl.cencosud.blogapp.home.domain.camera

import android.graphics.Bitmap

class UploadPhotoUseCase(private val repository: CameraRepository) {
    suspend fun invoke(imageBitmap: Bitmap, description: String) =
        repository.uploadPhoto(imageBitmap, description)
}