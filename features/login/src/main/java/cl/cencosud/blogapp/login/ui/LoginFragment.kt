package cl.cencosud.blogapp.login.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import cl.cencosud.blogapp.android.core.Result
import cl.cencosud.blogapp.login.R
import cl.cencosud.blogapp.login.data.LoginRepositoryImpl
import cl.cencosud.blogapp.login.data.RemoteDataSource
import cl.cencosud.blogapp.login.databinding.FragmentLoginBinding
import cl.cencosud.blogapp.login.presentation.LoginModelFactory
import cl.cencosud.blogapp.login.presentation.LoginViewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel> {
        LoginModelFactory(
            LoginRepositoryImpl(
                RemoteDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        doLogin()
        goToSignUpPage()
    }


    private fun doLogin() {
        binding.btnSignin.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            validateCredentials(email, password)
            signIn(email, password)
        }
    }

    private fun goToSignUpPage() {
        binding.txtSignup.setOnClickListener {
            findNavController().navigate(R.id.got_to_register)
            // findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
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

    private fun signIn(email: String, password: String) {
        viewModel.signIn(email, password).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnSignin.isEnabled = false
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Welcome ${result.data?.email}",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.got_to_home)
                    requireActivity().finish()
                }
                is Result.Failure -> {
                    binding.btnSignin.isEnabled = true
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Error: ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}