package com.kinderjoey.cookiez.ui.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.FragmentProfileBinding
import com.kinderjoey.cookiez.model.User
import com.kinderjoey.cookiez.ui.auth.AuthActivity
import com.kinderjoey.cookiez.ui.history.HistoryActivity
import com.kinderjoey.cookiez.ui.profile.wallet.CWalletActivity
import com.kinderjoey.cookiez.ui.splash.SplashActivity
import com.kinderjoey.cookiez.util.Constanta

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val uid = firebaseAuth.currentUser?.uid
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCookiezWallet.setOnClickListener {
            startActivity(Intent(requireActivity(), CWalletActivity::class.java))
        }
        binding.btnHistory.setOnClickListener {
            startActivity(Intent(requireActivity(), HistoryActivity::class.java))
        }
        uid?.let { viewModel.getUser(it).observe(viewLifecycleOwner, ::setProfile) }


    }

    @SuppressLint("SetTextI18n")
    private fun setProfile(user: User) {
        user.let { data ->
        with(binding){

            tvName.text = data.name
            tvEmail.text = data.email
            tvCoin.text = data.coin.toString()+" Coin"
            tvXp.text = data.xp.toString()+" XP"

            binding.btnUbahProfile.setOnClickListener {
                startActivity(
                    Intent(requireActivity(), EditProfileActivity::class.java)
                    .putExtra("USERNAME",data.name)
                    .putExtra("PHONE_NUMBER",data.phoneNumber)
                    .putExtra("AVATAR",data.avatar)
                )
            }

            binding.btnLocation.setOnClickListener {
                startActivity(
                    Intent(requireActivity(), EditAddressActivity::class.java)
                        .putExtra("ADDRESS",data.address)
                )
            }

            Glide.with(requireContext())
                .load(data.avatar)
                .placeholder(R.drawable.ilu_default_profile_picture)
                .apply(RequestOptions())
                .into(imgProfile)

            binding.btnLogout.setOnClickListener {
                viewModel.savePrefEmail()
                val intent = Intent(activity, AuthActivity::class.java)
                Constanta.SOURCE = Constanta.SOURCE_LOGOUT
                startActivity(intent)
                activity?.finish()
            }
        }

        }
    }
}