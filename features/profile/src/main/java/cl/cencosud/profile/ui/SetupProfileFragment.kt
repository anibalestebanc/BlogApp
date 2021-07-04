package cl.cencosud.profile.ui

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cl.cencosud.profile.R
import cl.cencosud.profile.databinding.FragmentSetupProfileBinding
import cl.cencosud.profile.presentation.ProfileUiState
import cl.cencosud.profile.presentation.ProfileViewModel
import cl.cencosud.profile.ui.utils.inject
import javax.inject.Inject

private const val REQUEST_IMAGE_CAPTURE = 1

class SetupProfileFragment : Fragment(R.layout.fragment_setup_profile) {

    private var bitmap: Bitmap? = null

    private val alertDialog by lazy {
        AlertDialog.Builder(requireContext())
            .setTitle("Uploading photo...")
            .create()
    }

    private var _binding: FragmentSetupProfileBinding? = null
    private val binding: FragmentSetupProfileBinding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ProfileViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSetupProfileBinding.bind(view)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}