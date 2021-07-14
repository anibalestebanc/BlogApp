package cl.cencosud.blogapp.home.ui.camera

import android.app.Activity
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
import cl.cencosud.blogapp.home.R
import cl.cencosud.blogapp.home.databinding.FragmentCameraBinding
import cl.cencosud.blogapp.home.presentation.camera.CameraUiState
import cl.cencosud.blogapp.home.presentation.camera.CameraViewModel
import cl.cencosud.blogapp.home.ui.utils.inject
import javax.inject.Inject

private const val REQUEST_IMAGE_CAPTURE = 2

class CameraFragment : Fragment(R.layout.fragment_camera) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CameraViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(CameraViewModel::class.java)
    }

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!

    private var bitmap: Bitmap? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCameraBinding.bind(view)

        setupObservers()

        binding.postImage.setOnClickListener {
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

        binding.btnUploadPhoto.setOnClickListener {
            bitmap?.let {
                viewModel.uploadPhoto(it, binding.etxtDescription.text.toString().trim())
            }
        }
    }

    private fun setupObservers() {
        viewModel.cameraStates.observe(viewLifecycleOwner, Observer(::renderUiStates))
    }

    private fun renderUiStates(uiState: CameraUiState) {
        when (uiState) {
            is CameraUiState.Loading -> {
                Toast.makeText(requireContext(), "Uploading photo...", Toast.LENGTH_SHORT).show()
            }
            is CameraUiState.Success -> {
                // findNavController().navigate(R.id.action_cameraFragment_to_homeScreenFragment)
            }
            is CameraUiState.Error -> {
                Toast.makeText(
                    requireContext(),
                    "Error ${uiState.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.postImage.setImageBitmap(imageBitmap)
            bitmap = imageBitmap
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}