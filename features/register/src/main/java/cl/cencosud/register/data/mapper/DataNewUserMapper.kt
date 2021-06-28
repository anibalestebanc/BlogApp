package cl.cencosud.register.data.mapper


import cl.cencosud.register.data.remote.model.DataNewUser
import cl.cencosud.register.domain.model.DomainNewUser

class DataNewUserMapper {

    fun DomainNewUser.fromDomainToData() = DataNewUser(
        email = email,
        username = username
    )
}