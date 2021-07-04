package cl.cencosud.blogapp.android.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import cl.cencosud.blogapp.android.R
import cl.cencosud.blogapp.android.core.Event
import cl.cencosud.blogapp.android.databinding.FragmentMainBinding
import cl.cencosud.blogapp.android.presentation.MainViewModel
import cl.cencosud.blogapp.android.presentation.model.MainEffect
import cl.cencosud.blogapp.android.presentation.model.MainUiState
import cl.cencosud.blogapp.android.ui.navigation.MainNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

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
                is MainEffect.GotoHome -> MainNavigator.navigateToHome(
                    binding.root, requireActivity() as MainActivity
                )
                is MainEffect.GoToLogin -> MainNavigator.navigateToLogin(binding.root)
            }
        }
    }

    private fun renderUiStates(uiState: MainUiState) {
        when (uiState) {
            MainUiState.ConnectionError -> {
            }
            MainUiState.DisplayUpdate -> {
            }
            is MainUiState.Error -> {
            }
            MainUiState.Loading -> {
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}