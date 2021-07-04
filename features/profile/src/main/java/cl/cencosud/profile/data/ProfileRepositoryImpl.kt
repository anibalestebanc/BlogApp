package cl.cencosud.profile.data

import android.graphics.Bitmap
import cl.cencosud.profile.domain.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val dataSource: ProfileDataSource) :
    ProfileRepository {
    override suspend fun updateProfile(imageBitmap: Bitmap, username: String) =
        dataSource.updateUserProfile(imageBitmap, username)
}