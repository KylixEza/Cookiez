package com.kinderjoey.demo.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.kinderjoey.demo.R
import com.kinderjoey.demo.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private val binding by viewBinding<FragmentProfileBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.includeAccountUtil.apply {
            ivIconUpside.setImageResource(R.drawable.ic_location_gray)
            ivIconDownside.setImageResource(R.drawable.ic_history)
            tvUpside.text = "Alamatku"
            tvDownside.text = "Riwayat Pesanan"
        }

        binding.includeOthersUtil.apply {
            ivIconUpside.setImageResource(R.drawable.ic_cwallet)
            ivIconDownside.setImageResource(R.drawable.ic_logout)
            tvUpside.text = "Cookiez Wallet"
            tvDownside.text = "Keluar"
        }

        binding.includeOthersUtil.ivForwardUpside.setOnClickListener {
            val intent = Intent(requireActivity(), CWalletActivity::class.java)
            startActivity(intent)
        }
    }
}