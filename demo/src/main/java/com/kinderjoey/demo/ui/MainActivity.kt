package com.kinderjoey.demo.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationBarView
import com.kinderjoey.demo.R
import com.kinderjoey.demo.databinding.ActivityMainBinding
import com.kinderjoey.demo.ui.home.HomeFragment
import com.kinderjoey.demo.ui.leaderboard.LeaderboardFragment
import com.kinderjoey.demo.ui.profile.ProfileFragment
import com.kinderjoey.demo.ui.voucher.VoucherFragment

class MainActivity : AppCompatActivity() {

    private val mainBinding by viewBinding<ActivityMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
        }

        val fragmentManager = supportFragmentManager
        val fragment = HomeFragment()

        fragmentManager.commit {
            add(R.id.main_frame_container, fragment, HomeFragment::class.java.simpleName)
        }

        mainBinding.mainBottomNav.apply {
            setOnNavigationItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.home_menu -> navigationSelected(HomeFragment())
                    R.id.voucher_menu -> navigationSelected(VoucherFragment())
                    R.id.leaderboard_menu -> navigationSelected(LeaderboardFragment())
                    R.id.profile_menu -> navigationSelected(ProfileFragment())
                    else -> false
                }
            }
            labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_UNLABELED
        }
    }

    private fun navigationSelected(fragment: Fragment): Boolean {
        supportFragmentManager.commit {
            replace(R.id.main_frame_container, fragment, fragment::class.java.simpleName)
        }
        return true
    }

    override fun onBackPressed() {
        if (mainBinding.mainBottomNav.selectedItemId != R.id.home_menu)
            mainBinding.mainBottomNav.selectedItemId = R.id.home_menu
        else
            super.onBackPressed()
    }

}