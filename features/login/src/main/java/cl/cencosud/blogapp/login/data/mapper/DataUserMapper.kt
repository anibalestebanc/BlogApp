package cl.cencosud.blogapp.login.data.mapper

import cl.cencosud.blogapp.login.data.remote.model.RemoteUser
import cl.cencosud.blogapp.userinfo.domain.model.DomainUser

class DataUserMapper {
    fun RemoteUser.fromRemoteToDomain() = DomainUser(
        email = email,
        username = username
    )
}