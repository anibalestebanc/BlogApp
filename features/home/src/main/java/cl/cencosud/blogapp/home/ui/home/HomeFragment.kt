package cl.cencosud.blogapp.home.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import cl.cencosud.blogapp.home.R
import cl.cencosud.blogapp.android.core.hide
import cl.cencosud.blogapp.android.core.show
import cl.cencosud.blogapp.home.data.home.remote.HomeRemoteImpl
import cl.cencosud.blogapp.home.data.home.HomeRepositoryImpl
import cl.cencosud.blogapp.home.databinding.FragmentHomeScreenBinding
import cl.cencosud.blogapp.home.presentation.home.HomeUiState
import cl.cencosud.blogapp.home.presentation.home.HomeViewModel
import cl.cencosud.blogapp.home.presentation.home.HomeViewModelFactory

class HomeFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding
    private val viewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory(
            HomeRepositoryImpl(
                HomeRemoteImpl()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeScreenBinding.bind(view)
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.homeStates.observe(viewLifecycleOwner, Observer(::renderUiStates))
    }

    private fun renderUiStates(homeUiState: HomeUiState) {
        when (homeUiState) {
            is HomeUiState.Loading -> binding.progressBar.show()
            is HomeUiState.Success -> {
                binding.progressBar.hide()
                binding.emptyContainer.hide()
                binding.rvHome.adapter = HomeAdapter(homeUiState.postList)
            }
            is HomeUiState.EmptyList -> {
                binding.progressBar.hide()
                binding.emptyContainer.show()
            }
            is HomeUiState.Error -> {
                binding.progressBar.hide()
                Toast.makeText(
                    requireContext(),
                    "Ocurrio un error: ${homeUiState.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}