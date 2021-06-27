package cl.cencosud.profile.ui

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import cl.cencosud.profile.*
import cl.cencosud.profile.data.ProfileDataSource
import cl.cencosud.profile.data.ProfileRepositoryImpl
import cl.cencosud.profile.databinding.FragmentSetupProfileBinding
import cl.cencosud.profile.presentation.ProfileViewModel
import cl.cencosud.profile.presentation.ProfileViewModelFactory
import cl.cencosud.profile.presentation.ProfileUiState

class SetupProfileFragment : Fragment(R.layout.fragment_setup_profile) {

    private lateinit var binding: FragmentSetupProfileBinding
    private val viewModel by viewModels<ProfileViewModel> {
        ProfileViewModelFactory(
            ProfileRepositoryImpl(
                ProfileDataSource()
            )
        )
    }
    private val REQUEST_IMAGE_CAPTURE = 1
    private var bitmap: Bitmap? = null

    private val alertDialog by lazy {
        AlertDialog.Builder(requireContext())
            .setTitle("Uploading photo...")
            .create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSetupProfileBinding.bind(view)
        setupObservers()

        binding.profileImage.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    requireContext(),
                    "No se encontro app para abir la camara",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.btnEditProfile.setOnClickListener {
            val username = binding.etxtUsername.text.toString().trim()
            bitmap?.let {
                if (username.isNotEmpty()) {
                    viewModel.updateUserProfile(imageBitmap = it, username = username)
                }
            }
        }
    }

    private fun setupObservers() {
        viewModel.profileStates.observe(viewLifecycleOwner, Observer(::renderUiStates))
    }

    private fun renderUiStates(uiState: ProfileUiState) {
        when (uiState) {
            is ProfileUiState.Loading -> {
                alertDialog.show()
            }
            is ProfileUiState.Success -> {
                alertDialog.dismiss()
                // findNavController().navigate(R.id.action_setupProfileFragment_to_homeScreenFragment)
            }
            is ProfileUiState.Error -> {
                alertDialog.dismiss()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.profileImage.setImageBitmap(imageBitmap)
            bitmap = imageBitmap
        }
    }

}