package cl.cencosud.register.data.remote.model

data class DataNewUser(
    val email: String,
    val username: String,
    val photo_url: String? = "Photo_URL.png"
)