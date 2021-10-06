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
import com.kinderjoey.cookiez.data.sources.dummy.DataDummy
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.databinding.FragmentHomeBinding
import com.kinderjoey.cookiez.ui.search.SearchActivity
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeBinding by viewBinding<FragmentHomeBinding>()
    private val viewModel: HomeViewModel by viewModel()
    private val categoryAdapter: CategoryAdapter by inject()
    private val promotionAdapter: PromotionAdapter by inject()
    private lateinit var popularAdapter: ListMenuAdapter
    private lateinit var exclusiveAdapter: ListMenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularAdapter = ListMenuAdapter()
        exclusiveAdapter = ListMenuAdapter()

        homeBinding.apply {
            cvSearchCover.setOnClickListener {
                startActivity(Intent(requireActivity(), SearchActivity::class.java))
            }
            rvCategory.apply {
                adapter = categoryAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                categoryAdapter.setAllData(DataDummy.setCategories())
            }
            rvCoupon.apply {
                adapter = promotionAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                promotionAdapter.setAllData(DataDummy.setPromotionCoupon())
            }
            rvPopularMenu.apply {
                adapter = popularAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                observePopularMenus()
            }
            rvExclusiveMenu.apply {
                adapter = exclusiveAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                observeExclusiveMenus()
            }
        }
    }

    private fun observeExclusiveMenus() {
        viewModel.getExclusiveMenus().observe(viewLifecycleOwner, {
            when(it) {
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> it.data?.let { it1 -> exclusiveAdapter.setAllData(it1) }
                is Resource.Empty -> {}
            }
        })
    }

    private fun observePopularMenus() {
        viewModel.getPopularMenus().observe(viewLifecycleOwner, {
            when(it) {
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> it.data?.let { it1 -> popularAdapter.setAllData(it1) }
                is Resource.Empty -> {}
            }
        })
    }
}