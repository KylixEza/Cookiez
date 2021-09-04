package com.kinderjoey.demo.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.kinderjoey.demo.R
import com.kinderjoey.demo.ui.auth.login.LoginFragment
import com.kinderjoey.demo.ui.auth.register.RegisterFragment

class AuthActivity : AppCompatActivity() {

    companion object {
        const val AUTH_INTENT_CODE = "AUTH_INTENT_CODE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val loginFragment = LoginFragment()
        val registerFragment = RegisterFragment()



        if (intent.getStringExtra(AUTH_INTENT_CODE) == LoginFragment::class.java.simpleName)
            addFragment(loginFragment)
        else
            addFragment(registerFragment)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            add(R.id.auth_container, fragment, fragment::class.java.simpleName)
        }
    }
}