package cl.cencosud.register.data.source

import cl.cencosud.register.data.remote.model.DataNewUser

interface RegisterRemote {
    suspend fun signUp(email: String, password: String): String
    suspend fun saveUser(userId: String, newUser: DataNewUser)
}