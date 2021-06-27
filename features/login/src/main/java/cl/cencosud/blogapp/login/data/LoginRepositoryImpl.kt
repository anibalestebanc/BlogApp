package cl.cencosud.blogapp.login.data

import cl.cencosud.blogapp.login.data.mapper.DataUserMapper
import cl.cencosud.blogapp.login.data.source.LoginRemote
import cl.cencosud.blogapp.login.domain.repository.LoginRepository
import cl.cencosud.blogapp.userinfo.data.source.UserCache
import cl.cencosud.blogapp.userinfo.domain.model.DomainUser

class LoginRepositoryImpl(
    private val remote: LoginRemote,
    private val cache: UserCache,
    private val mapper: DataUserMapper
) : LoginRepository {
    override suspend fun signIn(email: String, password: String): DomainUser {
        val domainUser = with(mapper) {
            remote.signIn(email, password).fromRemoteToDomain()
        }
        cache.saveUser(domainUser)
        return domainUser
    }
}