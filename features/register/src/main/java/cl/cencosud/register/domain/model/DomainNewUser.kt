package cl.cencosud.register.domain.model

data class DomainNewUser(
    val username: String,
    val password: String,
    val email: String
)