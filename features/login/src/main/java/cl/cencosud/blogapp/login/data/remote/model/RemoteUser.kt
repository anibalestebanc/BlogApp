package cl.cencosud.blogapp.login.data.remote.model

data class RemoteUser(
    val email: String,
    val username: String? = null,
    val id: String? = null,
    val photo_url: String? = null
)