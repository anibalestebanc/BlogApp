package cl.cencosud.blogapp.home.data.home.source

import cl.cencosud.blogapp.android.data.model.Post

interface HomeRemote {
    suspend fun getLatestPosts(): List<Post>
}