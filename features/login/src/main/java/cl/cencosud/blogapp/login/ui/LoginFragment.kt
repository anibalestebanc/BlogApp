package cl.cencosud.blogapp.login.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import cl.cencosud.blogapp.login.R
import cl.cencosud.blogapp.login.data.LoginRepositoryImpl
import cl.cencosud.blogapp.login.data.remote.LoginRemoteImpl
import cl.cencosud.blogapp.login.databinding.FragmentLoginBinding
import cl.cencosud.blogapp.login.presentation.LoginModelFactory
import cl.cencosud.blogapp.login.presentation.LoginUiState
import cl.cencosud.blogapp.login.presentation.LoginViewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel> {
        LoginModelFactory(
            LoginRepositoryImpl(
                LoginRemoteImpl()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        doLogin()
        goToSignUpPage()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.loginState.observe(viewLifecycleOwner, Observer(::renderUiState))
    }

    private fun renderUiState(loginUiState: LoginUiState) {
        when (loginUiState) {
            is LoginUiState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.btnSignin.isEnabled = false
            }
            is LoginUiState.Success -> {
                binding.progressBar.visibility = View.GONE
                findNavController().navigate(R.id.got_to_home)
                requireActivity().finish()
            }
            is LoginUiState.Error -> {
                binding.btnSignin.isEnabled = true
                binding.progressBar.visibility = View.GONE
                Toast.makeText(
                    requireContext(),
                    "Error: ${loginUiState.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun doLogin() {
        binding.btnSignin.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            validateCredentials(email, password)
            viewModel.signIn(email, password)
        }
    }

    private fun goToSignUpPage() {
        binding.txtSignup.setOnClickListener {
            findNavController().navigate(R.id.got_to_register)
        }
    }

    private fun validateCredentials(email: String, password: String) {
        if (email.isEmpty()) {
            binding.editTextEmail.error = "E-mail is empty"
            return
        }

        if (password.isEmpty()) {
            binding.editTextPassword.error = "Password is empty"
            return
        }
    }

}