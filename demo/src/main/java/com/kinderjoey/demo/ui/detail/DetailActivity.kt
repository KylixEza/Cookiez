package com.kinderjoey.demo.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.kinderjoey.demo.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val fragmentManager = supportFragmentManager
        val fragment = DetailMenuFragment()

        fragmentManager.commit {
            add(R.id.detail_container, fragment, DetailMenuFragment::class.java.simpleName)
        }

    }
}