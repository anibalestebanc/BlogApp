package cl.cencosud.register.data.mapper


import cl.cencosud.register.data.remote.model.DataNewUser
import cl.cencosud.register.domain.model.DomainNewUser
import javax.inject.Inject

class DataNewUserMapper @Inject constructor() {

    fun DomainNewUser.fromDomainToData() = DataNewUser(
        email = email,
        username = username
    )
}