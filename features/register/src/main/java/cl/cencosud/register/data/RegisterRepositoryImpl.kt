package cl.cencosud.register.data

import cl.cencosud.register.data.mapper.DataNewUserMapper
import cl.cencosud.register.data.source.RegisterRemote
import cl.cencosud.register.domain.model.DomainNewUser
import cl.cencosud.register.domain.repository.RegisterRepository
import com.google.firebase.auth.FirebaseUser

class RegisterRepositoryImpl(
    private val remote: RegisterRemote,
    private val mapper: DataNewUserMapper
) : RegisterRepository {

    override suspend fun signUp(newUser: DomainNewUser) {
        val userId = remote.signUp(newUser.email, newUser.password)
        val dataNewUser = with(mapper) { newUser.fromDomainToData() }
        remote.saveUser(userId, dataNewUser)
    }

}