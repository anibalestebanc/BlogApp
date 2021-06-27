package cl.cencosud.blogapp.home.domain.home

class GetLastPostsUseCase(private val repository: HomeRepository) {
    suspend fun invoke() = repository.getLatestPosts()
}