package cl.cencosud.blogapp.home.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cl.cencosud.blogapp.android.core.hide
import cl.cencosud.blogapp.android.core.show
import cl.cencosud.blogapp.home.R
import cl.cencosud.blogapp.home.databinding.FragmentHomeScreenBinding
import cl.cencosud.blogapp.home.presentation.home.HomeUiState
import cl.cencosud.blogapp.home.presentation.home.HomeViewModel
import cl.cencosud.blogapp.home.ui.utils.inject
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home_screen) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeScreenBinding.bind(view)
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.homeStates.observe(viewLifecycleOwner, Observer(::renderUiStates))
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}