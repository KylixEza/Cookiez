package com.kinderjoey.cookiez.ui.detail.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.adapter.ReviewAdapter
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.databinding.FragmentDetailMenuReviewBinding
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMenuReviewFragment : Fragment() {

    private val viewModel by viewModel<DetailMenuReviewViewModel>()
    private val binding by viewBinding<FragmentDetailMenuReviewBinding>()
    private val reviewAdapter by inject<ReviewAdapter>()
    private var menuName: String? = null

    companion object {
        private const val KEY_BUNDLE = "MENU_NAME"

        fun getInstance(menuName: String): Fragment {
            return DetailMenuReviewFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_BUNDLE, menuName)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            menuName = it?.getString(KEY_BUNDLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_menu_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvListOfReview.apply {
            adapter = reviewAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        observeReview()
    }

    private fun observeReview() {
        viewModel.getReviews(menuName.toString()).observe(viewLifecycleOwner, {
            when(it) {
                is Resource.Empty -> {}
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    it.data?.let { it1 -> reviewAdapter.setAllData(it1) }
                    binding.tvNumberOfRating.text = String.format("Dirating oleh ${it.data?.size} orang")

                    var avgRating = 0.0
                    it.data?.forEach { review ->
                        avgRating += review.starReviewer
                    }
                    avgRating /= it.data?.size!!
                    binding.tvAverageRating.text = String.format("%.1f".format(avgRating))
                    binding.ratingBar.rating = avgRating.toFloat()
                }
            }
        })
    }
}