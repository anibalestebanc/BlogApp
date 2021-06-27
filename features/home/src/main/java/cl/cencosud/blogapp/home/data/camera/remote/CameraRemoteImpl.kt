package cl.cencosud.blogapp.home.data.camera.remote

import android.graphics.Bitmap
import cl.cencosud.blogapp.android.data.model.Post
import cl.cencosud.blogapp.home.data.camera.source.CameraRemote
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.util.*

class CameraRemoteImpl : CameraRemote {

    override suspend fun uploadPhoto(imageBitmap: Bitmap, description: String) {
        val user = FirebaseAuth.getInstance().currentUser
        val randomName = UUID.randomUUID().toString()
        val imageRef =
            FirebaseStorage.getInstance().reference.child("${user?.uid}/posts/$randomName")
        val baos = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        var downloadUrl = ""
        withContext(Dispatchers.IO) {
            downloadUrl =
                imageRef.putBytes(baos.toByteArray()).await().storage.downloadUrl.await().toString()
        }
        user?.let {
            it.displayName?.let { displayName ->
                FirebaseFirestore.getInstance().collection("posts").add(
                    Post(
                        profile_name = displayName,
                        profile_picture = it.photoUrl.toString(),
                        post_image = downloadUrl,
                        post_description = description,
                        uid = user.uid
                    )
                )
            }
        }

    }
}