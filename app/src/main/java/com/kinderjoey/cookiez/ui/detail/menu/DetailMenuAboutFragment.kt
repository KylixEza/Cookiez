package com.kinderjoey.cookiez.ui.detail.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.viewModels
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.databinding.FragmentDetailMenuAboutBinding

class DetailMenuAboutFragment : Fragment() {

    private val viewModel by viewModels<DetailMenuAboutViewModel>()
    private val binding by viewBinding<FragmentDetailMenuAboutBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_menu_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuName = ""
        observeDetail(menuName)
    }

    private fun observeDetail(menuName: String) {
        viewModel.getDetailMenu(menuName).observe(viewLifecycleOwner, {
            when(it) {
                is Resource.Empty -> {}
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    binding.tvMenuDescription.text = it.data?.description
                    binding.tvMenuEstimateTime.text = it.data?.estimatedTime
                    binding.tvMenuBenefit.text = it.data?.benefit
                }
            }
        })
    }
}