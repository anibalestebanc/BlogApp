package cl.cencosud.blogapp.login.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import cl.cencosud.blogapp.login.R
import cl.cencosud.blogapp.login.databinding.FragmentLoginBinding
import cl.cencosud.blogapp.login.presentation.model.LoginUiState
import cl.cencosud.blogapp.login.presentation.LoginViewModel
import cl.cencosud.blogapp.login.ui.utils.inject
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.fragment_login) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<LoginViewModel> {
        viewModelFactory
    }

    /* private val viewModel: LoginViewModel by lazy {
         ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
     }*/

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}