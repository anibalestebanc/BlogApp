package cl.cencosud.blogapp.userinfo.domain.model

data class DomainUser(
    val email: String,
    val username: String? = null,
    val id: String? = null,
    val photo_url: String? = null
)