package cl.cencosud.blogapp.home.domain.home

import cl.cencosud.blogapp.android.core.Result
import cl.cencosud.blogapp.android.data.model.Post

interface HomeScreenRepo {
    suspend fun getLatestPosts(): Result<List<Post>>
}