package cl.cencosud.home.data

import android.graphics.Bitmap
import cl.cencosud.home.data.CameraDataSource

class CameraRepoImpl(private val dataSource: cl.cencosud.home.data.CameraDataSource):
    cl.cencosud.home.domain.CameraRepo {
    override suspend fun uploadPhoto(imageBitmap: Bitmap, description: String) {
        dataSource.uploadPhoto(imageBitmap, description)
    }
}