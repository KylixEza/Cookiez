package com.kinderjoey.cookiez.ui.detail.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.viewModels
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.adapter.ReviewAdapter
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.databinding.FragmentDetailMenuReviewBinding
import org.koin.android.ext.android.inject

class DetailMenuReviewFragment : Fragment() {

    private val viewModel by viewModels<DetailMenuReviewViewModel>()
    private val binding by viewBinding<FragmentDetailMenuReviewBinding>()
    private val reviewAdapter by inject<ReviewAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_menu_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuName = ""
        observeReview(menuName)
    }

    private fun observeReview(menuName: String) {
        viewModel.getReviews(menuName).observe(viewLifecycleOwner, {
            when(it) {
                is Resource.Empty -> TODO()
                is Resource.Error -> TODO()
                is Resource.Loading -> TODO()
                is Resource.Success -> it.data?.let { it1 -> reviewAdapter.setAllData(it1) }
            }
        })
    }
}