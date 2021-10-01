package com.kinderjoey.cookiez.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.adapter.CategoryAdapter
import com.kinderjoey.cookiez.adapter.ListMenuAdapter
import com.kinderjoey.cookiez.adapter.PromotionAdapter
import com.kinderjoey.cookiez.databinding.FragmentHomeBinding
import com.kinderjoey.cookiez.ui.search.SearchActivity
import com.kinderjoey.cookiez.util.DataDummy

class HomeFragment : Fragment() {

    private val homeBinding by viewBinding<FragmentHomeBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = CategoryAdapter()
        val promotionAdapter = PromotionAdapter()
        val popularAdapter = ListMenuAdapter()
        val exclusiveAdapter = ListMenuAdapter()

        homeBinding.apply {
            cvSearchCover.setOnClickListener {
                startActivity(Intent(requireActivity(), SearchActivity::class.java))
            }
            rvCategory.apply {
                categoryAdapter.setAllData(DataDummy.setCategories())
                adapter = categoryAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            }
            rvCoupon.apply {
                promotionAdapter.setAllData(DataDummy.setPromotionCoupon())
                adapter = promotionAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            }
            rvPopularMenu.apply {
                adapter = popularAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            }
            rvExclusiveMenu.apply {
                adapter = exclusiveAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            }

            homeBinding
        }
    }
}