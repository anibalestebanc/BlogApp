package cl.cencosud.blogapp.home.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import cl.cencosud.blogapp.home.R
import cl.cencosud.blogapp.android.core.Result
import cl.cencosud.blogapp.android.core.hide
import cl.cencosud.blogapp.android.core.show
import cl.cencosud.blogapp.home.data.home.HomeScreenDataSource
import cl.cencosud.blogapp.home.data.home.HomeScreenRepoImpl
import cl.cencosud.blogapp.home.databinding.FragmentHomeScreenBinding
import cl.cencosud.blogapp.home.presentation.HomeScreenViewModel
import cl.cencosud.blogapp.home.presentation.HomeScreenViewModelFactory

class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding
    private val viewModel by viewModels<HomeScreenViewModel> {
        HomeScreenViewModelFactory(
                HomeScreenRepoImpl(
                        HomeScreenDataSource()
                )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeScreenBinding.bind(view)

        viewModel.fetchLatestPosts().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.show()
                }

                is Result.Success -> {
                    binding.progressBar.hide()
                    if(result.data.isEmpty()) {
                        binding.emptyContainer.show()
                        return@Observer
                    }else{
                        binding.emptyContainer.hide()
                    }
                    binding.rvHome.adapter = HomeScreenAdapter(result.data)
                }

                is Result.Failure -> {
                    binding.progressBar.hide()
                    Toast.makeText(
                            requireContext(),
                            "Ocurrio un error: ${result.exception}",
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}