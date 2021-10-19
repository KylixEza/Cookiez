package com.kinderjoey.cookiez.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.adapter.CategoryAdapter
import com.kinderjoey.cookiez.adapter.ListMenuAdapter
import com.kinderjoey.cookiez.adapter.PromotionAdapter
import com.kinderjoey.cookiez.data.sources.dummy.DataDummy
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.databinding.FragmentHomeBinding
import com.kinderjoey.cookiez.ui.profile.ProfileViewModel
import com.kinderjoey.cookiez.ui.search.SearchActivity
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeBinding by viewBinding<FragmentHomeBinding>()
    private val viewModel: HomeViewModel by viewModel()
    private val categoryAdapter: CategoryAdapter by inject()
    private val promotionAdapter: PromotionAdapter by inject()
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val profileViewModel: ProfileViewModel by viewModels()
    private val uid = firebaseAuth.currentUser?.uid
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

        uid?.let {
            profileViewModel.getUser(it).observe(viewLifecycleOwner, { user ->
                homeBinding.tvLocationPoint.text = user.address
            })
        }

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
            includePopular.rvPopularMenu.apply {
                adapter = popularAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                observePopularMenus()
            }
            includeExclusive.rvExclusiveMenu.apply {
                adapter = exclusiveAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                observeExclusiveMenus()
            }
        }

        homeBinding.ivFavorite.setOnClickListener {
            view.findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToFavoriteActivity())
        }
        homeBinding.ivNotification.setOnClickListener {
            view.findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNotificationActivity())
        }
    }

    private fun observeExclusiveMenus() {
        viewModel.getExclusiveMenus().observe(viewLifecycleOwner, {
            when(it) {
                is Resource.Error -> {
                    homeBinding.includeExclusive.tvErrorMessage.text = it.message
                    homeBinding.includeExclusive.progressPopular.visibility = View.INVISIBLE
                }
                is Resource.Loading -> homeBinding.includeExclusive.progressPopular.visibility = View.VISIBLE
                is Resource.Success -> {
                    it.data?.let { it1 -> exclusiveAdapter.setAllData(it1) }
                    homeBinding.includeExclusive.progressPopular.visibility = View.INVISIBLE
                }
                is Resource.Empty -> {
                    homeBinding.includeExclusive.tvErrorMessage.text = "Tidak ada menu ekslusif"
                    homeBinding.includeExclusive.progressPopular.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun observePopularMenus() {
        viewModel.getPopularMenus().observe(viewLifecycleOwner, {
            when(it) {
                is Resource.Error -> {
                    homeBinding.includePopular.tvErrorMessage.text = it.message
                    homeBinding.includePopular.progressPopular.visibility = View.INVISIBLE
                }
                is Resource.Loading -> homeBinding.includePopular.progressPopular.visibility = View.VISIBLE
                is Resource.Success -> {
                    it.data?.let { it1 -> popularAdapter.setAllData(it1) }
                    homeBinding.includePopular.progressPopular.visibility = View.INVISIBLE
                }
                is Resource.Empty -> {
                    homeBinding.includePopular.tvErrorMessage.text = "Tidak ada menu populer"
                    homeBinding.includePopular.progressPopular.visibility = View.INVISIBLE
                }
            }
        })
    }
}