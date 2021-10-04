package com.kinderjoey.cookiez.ui.auth

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.NavHost
import androidx.navigation.navArgs
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.ActivityAuthBinding
import com.kinderjoey.cookiez.ui.auth.login.LoginFragment
import com.kinderjoey.cookiez.ui.auth.register.RegisterFragment

class AuthActivity : AppCompatActivity() {

    private val binding: ActivityAuthBinding by viewBinding()
    private val args: AuthActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
        }

        val navHost = binding.fragmentContainerView as NavHost
        val graph = navHost.navController.navInflater.inflate(R.navigation.auth_navigation)

        val authIntentCode = args.authIntentCode

        if (authIntentCode == RegisterFragment::class.java.simpleName)
            graph.startDestination = R.id.registerFragment
        else
            graph.startDestination = R.id.loginFragment
    }
}