package com.kinderjoey.cookiez.ui.detail.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.databinding.FragmentDetailMenuAboutBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMenuAboutFragment : Fragment() {

    private val viewModel by viewModel<DetailMenuAboutViewModel>()
    private val binding by viewBinding<FragmentDetailMenuAboutBinding>()
    private var menuName: String? = null

    companion object {
        private const val KEY_BUNDLE = "MENU_NAME"

        fun getInstance(menuName: String): Fragment {
            return DetailMenuAboutFragment().apply {
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
        return inflater.inflate(R.layout.fragment_detail_menu_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeDetail()
    }

    private fun observeDetail() {
        viewModel.getDetailMenu(menuName.toString()).observe(viewLifecycleOwner, {
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