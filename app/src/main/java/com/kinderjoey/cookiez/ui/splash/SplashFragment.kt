package com.kinderjoey.cookiez.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.ui.MainActivity

class SplashFragment : Fragment() {

    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().window.statusBarColor = View.GONE
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({observeHaveRunAppBefore(view)}, 2000)
    }

    private fun observeUsername(view: View) {
        splashViewModel.readPrefUsername().observe(viewLifecycleOwner, {isLogin ->
            if (isLogin) {
                view.findNavController().navigate(R.id.action_splashFragment_to_baseActivity)
            } else {
                view.findNavController().navigate(R.id.action_splashFragment_to_authActivity)
            }
        })
    }

    private fun observeHaveRunAppBefore(view: View) {
        splashViewModel.readPrefHaveRunAppBefore().observe(viewLifecycleOwner, { haveRun ->
            if (haveRun) {
                observeUsername(view)
            } else {
                view.findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
            }
        })
    }
}