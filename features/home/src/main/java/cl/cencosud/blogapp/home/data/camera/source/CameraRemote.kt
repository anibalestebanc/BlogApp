package cl.cencosud.blogapp.home.data.camera.source

import android.graphics.Bitmap

interface CameraRemote {
    suspend fun uploadPhoto(imageBitmap: Bitmap, description: String)
}