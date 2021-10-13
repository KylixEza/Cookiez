package com.kinderjoey.cookiez.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kinderjoey.cookiez.ui.detail.DetailMenuFragment
import com.kinderjoey.cookiez.ui.detail.menu.DetailMenuAboutFragment
import com.kinderjoey.cookiez.ui.detail.menu.DetailMenuReviewFragment
import com.kinderjoey.cookiez.ui.detail.menu.DetailMenuTutorialFragment

class DetailMenuPageAdapter (
    fm: FragmentManager,
    lifeCycle: Lifecycle,
    private val menuName: String
): FragmentStateAdapter(fm, lifeCycle) {


    override fun getItemCount(): Int = DetailMenuFragment.TAB_TITLES.size

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = DetailMenuTutorialFragment.getInstance(menuName)
            1 -> fragment = DetailMenuAboutFragment.getInstance(menuName)
            2 -> fragment = DetailMenuReviewFragment.getInstance(menuName)
        }
        return fragment as Fragment
    }
}