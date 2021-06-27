package cl.cencosud.blogapp.home.ui.myaccount

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import cl.cencosud.blogapp.android.BlogApplication
import cl.cencosud.blogapp.home.R
import cl.cencosud.blogapp.home.databinding.FragmentMyAccountBinding
import cl.cencosud.blogapp.userinfo.data.source.UserCache
import com.bumptech.glide.Glide

class MyAccountFragment : Fragment(R.layout.fragment_my_account) {

    private var _binding: FragmentMyAccountBinding? = null
    private val binding get() = _binding!!

    private val userCache: UserCache by lazy {
        (requireActivity().application as BlogApplication).userInfo
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMyAccountBinding.bind(view)
        binding.editProfileText.setOnClickListener {
            MyAccountNavigator.navigateToProfile(binding.root)
        }
        setUserData()
    }

    private fun setUserData() {
        with(userCache.getUser()) {
            binding.usernameText.text = username
            binding.emailText.text = email
            Glide.with(requireContext())
                .load(photo_url)
                .centerCrop()
                .placeholder(R.drawable.ic_account_circle)
                .into(binding.profileImage)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}