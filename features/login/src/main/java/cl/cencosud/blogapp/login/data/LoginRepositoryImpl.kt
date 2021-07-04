package cl.cencosud.blogapp.login.data

import cl.cencosud.blogapp.login.data.mapper.DataUserMapper
import cl.cencosud.blogapp.login.data.remote.model.RemoteUser
import cl.cencosud.blogapp.login.data.source.LoginRemote
import cl.cencosud.blogapp.login.domain.repository.LoginRepository
import cl.cencosud.blogapp.userinfo.data.source.UserCache
import cl.cencosud.blogapp.userinfo.domain.model.DomainUser
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor (
    private val remote: LoginRemote,
    private val cache: UserCache,
    private val mapper: DataUserMapper
) : LoginRepository {
    override suspend fun signIn(email: String, password: String): DomainUser {
        val userId = remote.signIn(email, password)
        val remoteUser : RemoteUser = remote.getUser(userId)?: throw Exception("Error to get user")
        val domainUser = with(mapper){remoteUser.fromRemoteToDomain(userId)}
        cache.saveUser(domainUser)
        return domainUser
    }
}