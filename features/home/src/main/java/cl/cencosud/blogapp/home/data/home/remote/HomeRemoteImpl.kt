package cl.cencosud.blogapp.home.data.home.remote

import cl.cencosud.blogapp.android.data.model.Post
import cl.cencosud.blogapp.home.data.home.source.HomeRemote
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeRemoteImpl : HomeRemote {

    override suspend fun getLatestPosts(): List<Post> = withContext(Dispatchers.IO) {
        val postList = mutableListOf<Post>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("posts").get().await()
        for (post in querySnapshot.documents) {
            post.toObject(Post::class.java)?.let { fbPost ->
                fbPost.apply {
                    created_at = post.getTimestamp(
                        "created_at",
                        DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                    )?.toDate()
                }
                postList.add(fbPost)
            }
        }
        postList
    }


}