package com.kinderjoey.demo.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.kinderjoey.demo.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val fragmentManager = supportFragmentManager
        val splashFragment = SplashFragment()

        fragmentManager.commit {
            add(R.id.splash_fragment_container, splashFragment, SplashFragment::class.java.simpleName)
        }
    }
}