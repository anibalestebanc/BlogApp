package cl.cencosud.register.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cl.cencosud.register.R
import cl.cencosud.register.databinding.FragmentRegisterBinding
import cl.cencosud.register.domain.model.DomainNewUser
import cl.cencosud.register.presentation.RegisterUiState
import cl.cencosud.register.presentation.RegisterViewModel
import cl.cencosud.register.ui.utils.inject
import javax.inject.Inject

class RegisterFragment : Fragment(R.layout.fragment_register) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegisterBinding.bind(view)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}