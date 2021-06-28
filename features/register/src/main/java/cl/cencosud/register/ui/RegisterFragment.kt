package cl.cencosud.register.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import cl.cencosud.register.R
import cl.cencosud.register.data.RegisterRepositoryImpl
import cl.cencosud.register.data.mapper.DataNewUserMapper
import cl.cencosud.register.data.remote.RegisterRemoteImpl
import cl.cencosud.register.databinding.FragmentRegisterBinding
import cl.cencosud.register.domain.SignUpUseCase
import cl.cencosud.register.domain.model.DomainNewUser
import cl.cencosud.register.presentation.RegisterModelFactory
import cl.cencosud.register.presentation.RegisterViewModel
import cl.cencosud.register.presentation.RegisterUiState

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding

    private val viewModel by viewModels<RegisterViewModel> {
        RegisterModelFactory(
            SignUpUseCase(
                RegisterRepositoryImpl(
                    RegisterRemoteImpl(),
                    DataNewUserMapper()
                )
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        signUp()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.registerStates.observe(viewLifecycleOwner, Observer(::renderStates))
    }

    private fun renderStates(uiState: RegisterUiState) {
        when (uiState) {
            is RegisterUiState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.btnSignup.isEnabled = false
            }
            is RegisterUiState.Success -> {
                binding.progressBar.visibility = View.GONE
                requireActivity().onBackPressed()
            }
            is RegisterUiState.Error -> {
                binding.btnSignup.isEnabled = true
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun signUp() {
        binding.btnSignup.setOnClickListener {

            val username = binding.editTextUsername.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()

            if (validateCredentials(
                    password,
                    confirmPassword,
                    username,
                    email
                )
            ) return@setOnClickListener
            createUser(DomainNewUser(username, password, email))
        }
    }

    private fun createUser(newUser: DomainNewUser) {
        viewModel.signUp(newUser)
    }

    private fun validateCredentials(
        password: String,
        confirmPassword: String,
        username: String,
        email: String
    ): Boolean {
        if (password != confirmPassword) {
            binding.editTextConfirmPassword.error = "Password does not match"
            binding.editTextPassword.error = "Password does not match"
            return true
        }

        if (username.isEmpty()) {
            binding.editTextUsername.error = "Password is empty"
            return true
        }

        if (email.isEmpty()) {
            binding.editTextEmail.error = "Password is empty"
            return true
        }

        if (password.isEmpty()) {
            binding.editTextPassword.error = "Password is empty"
            return true
        }

        if (confirmPassword.isEmpty()) {
            binding.editTextConfirmPassword.error = "Password is empty"
            return true
        }
        return false
    }
}