package cl.cencosud.blogapp.login.data.mapper

import cl.cencosud.blogapp.login.data.remote.model.RemoteUser
import cl.cencosud.blogapp.userinfo.domain.model.DomainUser

class DataUserMapper {
    fun RemoteUser.fromRemoteToDomain(userId: String) = DomainUser(
        email = email,
        username = username,
        photo_url = photo_url,
        id = userId
    )
}