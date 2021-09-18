package com.kinderjoey.cookiez.ui.splash

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import com.kinderjoey.cookiez.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
        }

        val fragmentManager = supportFragmentManager
        val splashFragment = SplashFragment()

        fragmentManager.commit {
            add(R.id.splash_fragment_container, splashFragment, SplashFragment::class.java.simpleName)
        }
    }
}