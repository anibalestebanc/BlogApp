package cl.cencosud.register.domain

class SignUpUseCase(private val repository: RegisterRepository) {
    suspend fun invoke(email: String, password: String, username: String) =
        repository.signUp(email, password, username)
}