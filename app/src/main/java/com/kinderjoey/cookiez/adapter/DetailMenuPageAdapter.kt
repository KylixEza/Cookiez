package com.kinderjoey.cookiez.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailMenuPageAdapter (
    fm: FragmentManager,
    lifeCycle: Lifecycle
): FragmentStateAdapter(fm, lifeCycle) {

    private val listOfFragment = ArrayList<Fragment>()

    fun setFragments(fm: List<Fragment>) {
        listOfFragment.apply {
            clear()
            addAll(fm)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listOfFragment.size

    override fun createFragment(position: Int): Fragment = listOfFragment[position]
}