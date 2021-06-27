package cl.cencosud.blogapp.android.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import cl.cencosud.blogapp.android.BlogApplication
import cl.cencosud.blogapp.android.R
import cl.cencosud.blogapp.android.core.Event
import cl.cencosud.blogapp.android.databinding.FragmentMainBinding
import cl.cencosud.blogapp.android.presentation.MainEffect
import cl.cencosud.blogapp.android.presentation.MainUiState
import cl.cencosud.blogapp.android.presentation.MainViewModel
import cl.cencosud.blogapp.android.presentation.MainViewModelFactory
import cl.cencosud.blogapp.userinfo.data.UserRepositoryImpl
import cl.cencosud.blogapp.userinfo.data.source.UserCache
import cl.cencosud.blogapp.userinfo.domain.GetUserUseCase

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding get() = _binding!!
    private var _binding: FragmentMainBinding? = null

    private val userCache: UserCache by lazy {
        (requireActivity().application as BlogApplication).userInfo
    }

    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(
            GetUserUseCase(
                UserRepositoryImpl(
                    userCache
                )
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.mainStates.observe(viewLifecycleOwner, Observer(::renderUiStates))
        viewModel.mainEffect.observe(viewLifecycleOwner, Observer(::managementEffect))
    }

    private fun managementEffect(event: Event<MainEffect>) {
        event.getContentIfNotHandled()?.let { mainEffect ->
            when (mainEffect) {
                is MainEffect.GotoHome -> {
                    findNavController().navigate(R.id.go_to_home)
                    requireActivity().finish()
                }
                is MainEffect.GoToLogin -> findNavController().navigate(R.id.go_to_login)
            }
        }
    }

    private fun renderUiStates(uiState: MainUiState) {
        when (uiState) {
            MainUiState.ConnectionError -> {}
            MainUiState.DisplayUpdate -> {}
            is MainUiState.Error -> {}
            MainUiState.Loading -> {}
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}