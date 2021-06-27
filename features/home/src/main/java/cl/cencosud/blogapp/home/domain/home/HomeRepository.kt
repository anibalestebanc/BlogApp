package cl.cencosud.blogapp.home.domain.home

import cl.cencosud.blogapp.android.data.model.Post

interface HomeRepository {
    suspend fun getLatestPosts(): List<Post>
}