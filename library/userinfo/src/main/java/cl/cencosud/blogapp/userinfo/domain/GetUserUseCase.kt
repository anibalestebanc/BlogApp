package cl.cencosud.blogapp.userinfo.domain

import cl.cencosud.blogapp.userinfo.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor (private val repository: UserRepository) {
    suspend fun invoke() = repository.getLocalUser()
}