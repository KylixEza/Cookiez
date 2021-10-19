package com.kinderjoey.cookiez.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.ui.auth.login.LoginFragment

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        supportFragmentManager.commit {
            add(R.id.auth_container, LoginFragment())
        }
    }
}