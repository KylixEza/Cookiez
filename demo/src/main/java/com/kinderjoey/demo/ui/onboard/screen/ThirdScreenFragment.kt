package com.kinderjoey.demo.ui.onboard.screen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.viewModels
import com.kinderjoey.demo.ui.MainActivity
import com.kinderjoey.demo.R
import com.kinderjoey.demo.databinding.FragmentThirdScreenBinding
import com.kinderjoey.demo.ui.auth.AuthActivity
import com.kinderjoey.demo.ui.splash.SplashViewModel

class ThirdScreenFragment : Fragment() {

    private val thirdScreenBinding by viewBinding<FragmentThirdScreenBinding>()
    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().window.statusBarColor = View.GONE
        return inflater.inflate(R.layout.fragment_third_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thirdScreenBinding.apply {
            btnLogin.setOnClickListener {
                val intent = Intent(requireActivity(), AuthActivity::class.java)
                startActivity(intent)
            }
            btnRegister.setOnClickListener {
                splashViewModel.saveToDataStore("Kylix")
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}