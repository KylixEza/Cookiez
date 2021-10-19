package com.kinderjoey.cookiez.ui.splash

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.fragment.app.commit
import androidx.navigation.fragment.NavHostFragment
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.ActivitySplashBinding
import com.kinderjoey.cookiez.util.Constanta

class SplashActivity : AppCompatActivity() {

    val binding: ActivitySplashBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
        }

        val isLogout = intent.getBooleanExtra(Constanta.LOGOUT, false)
        
        if (isLogout) {
            val navHostFragment = (supportFragmentManager.findFragmentById(R.id.splash_navigation) as NavHostFragment)
            val inflater = navHostFragment.navController.navInflater
            val graph = inflater.inflate(R.navigation.splash_navigation)
            graph.startDestination = R.id.login_destination
            navHostFragment.navController.graph = graph
        }
    }
}