package com.kinderjoey.demo.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.kinderjoey.demo.R
import com.kinderjoey.demo.ui.auth.login.LoginFragment

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val mFragmentManager = supportFragmentManager
        val mFragment = LoginFragment()

        mFragmentManager.commit {
            add(R.id.auth_container, mFragment, LoginFragment::class.java.simpleName)
        }
    }
}