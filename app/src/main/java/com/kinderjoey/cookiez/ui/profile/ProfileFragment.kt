package com.kinderjoey.cookiez.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.FragmentProfileBinding
import com.kinderjoey.cookiez.ui.history.HistoryActivity
import com.kinderjoey.cookiez.ui.profile.wallet.CWalletActivity


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

        binding.btnCookiezWallet.setOnClickListener {
            startActivity(Intent(requireActivity(), CWalletActivity::class.java))
        }
        binding.btnHistory.setOnClickListener {
            startActivity(Intent(requireActivity(), HistoryActivity::class.java))
        }
    }
}