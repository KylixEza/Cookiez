package com.kinderjoey.demo.ui.onboard.screen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.viewModels
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.FragmentThirdScreenBinding
import com.kinderjoey.cookiez.ui.auth.AuthActivity
import com.kinderjoey.cookiez.ui.auth.login.LoginFragment
import com.kinderjoey.cookiez.ui.auth.register.RegisterFragment
import com.kinderjoey.cookiez.ui.splash.SplashViewModel

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
                intent.putExtra(AuthActivity.AUTH_INTENT_CODE, LoginFragment::class.java.simpleName)
                startActivity(intent)
            }
            btnRegister.setOnClickListener {
                val intent = Intent(requireActivity(), AuthActivity::class.java)
                intent.putExtra(AuthActivity.AUTH_INTENT_CODE, RegisterFragment::class.java.simpleName)
                startActivity(intent)
            }
        }
    }
}