package com.kinderjoey.cookiez.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.kinderjoey.cookiez.R
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private val splashViewModel by viewModel<SplashViewModel>()

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
        handler.postDelayed({
            observeHaveRunAppBefore(view)
        }, 2000L)
    }

    private fun observeUsername(view: View) {
        splashViewModel.readPrefEmail().observe(viewLifecycleOwner, { isLogin ->
            if (isLogin) {
                view.findNavController().navigate(R.id.action_splashFragment_to_baseActivity)
                activity?.finish()
            } else {
                view.findNavController().navigate(R.id.action_splash_destination_to_loginFragment)
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