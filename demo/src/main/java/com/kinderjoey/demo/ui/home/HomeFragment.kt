package com.kinderjoey.demo.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinderjoey.demo.R
import com.kinderjoey.demo.data.DataDummy
import com.kinderjoey.demo.databinding.FragmentHomeBinding
import com.kinderjoey.demo.ui.home.adapter.CategoryAdapter
import com.kinderjoey.demo.ui.home.adapter.ListMenuAdapter
import com.kinderjoey.demo.ui.home.adapter.PromotionAdapter

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
            includeAppBar.tvLocation.text = "Kota Malang"
            rvCategory.apply {
                adapter = categoryAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            }
            rvCoupon.apply {
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
        }

        categoryAdapter.setAllData(DataDummy.setCategories())
        promotionAdapter.setAllData(DataDummy.setPromotionCoupon())
        popularAdapter.setAllData(DataDummy.setPopularMenu())
        exclusiveAdapter.setAllData(DataDummy.setExclusive())

    }
}