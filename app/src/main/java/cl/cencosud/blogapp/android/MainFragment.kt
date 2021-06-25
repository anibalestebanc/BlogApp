package cl.cencosud.blogapp.android

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cl.cencosud.blogapp.android.databinding.FragmentMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding get() = _binding!!
    private var _binding: FragmentMainBinding? = null
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        isUserLoggedIn()
    }

    private fun isUserLoggedIn() {
        if (firebaseAuth.currentUser == null) {
            findNavController().navigate(R.id.go_to_login)
        } else {
            findNavController().navigate(R.id.go_to_home)
            requireActivity().finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}