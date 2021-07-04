package cl.cencosud.blogapp.userinfo.data

import cl.cencosud.blogapp.userinfo.data.source.UserCache
import cl.cencosud.blogapp.userinfo.domain.model.DomainUser
import cl.cencosud.blogapp.userinfo.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor (private val cache: UserCache) : UserRepository {
    override suspend fun getLocalUser(): DomainUser = cache.getUser()
}