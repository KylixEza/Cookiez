package com.kinderjoey.cookiez.ui.detail.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.adapter.IngredientAdapter
import com.kinderjoey.cookiez.adapter.StepAdapter
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.databinding.FragmentDetailMenuTutorialBinding
import org.koin.android.ext.android.inject

class DetailMenuTutorialFragment : Fragment() {

    private val binding by viewBinding<FragmentDetailMenuTutorialBinding>()
    private val viewModel by viewModels<DetailMenuTutorialViewModel>()
    private val stepAdapter by inject<StepAdapter>()
    private val ingredientAdapter by inject<IngredientAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_menu_tutorial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            rvSteps.apply {
                adapter = stepAdapter
                layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            }
            rvIngredients.apply {
                adapter = ingredientAdapter
                layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            }
        }

        val menuName = ""
        observeIngredient(menuName)
        observeStep(menuName)
    }

    private fun observeIngredient(menuName: String) {
        viewModel.getIngredients(menuName).observe(viewLifecycleOwner, {
            when(it) {
                is Resource.Empty -> {}
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> it.data?.ingredients?.let { ingredient ->
                    ingredientAdapter.setAllData(
                        ingredient
                    )
                }
            }
        })
    }

    private fun observeStep(menuName: String) {
        viewModel.getSteps(menuName).observe(viewLifecycleOwner, {
            when(it) {
                is Resource.Empty -> TODO()
                is Resource.Error -> TODO()
                is Resource.Loading -> TODO()
                is Resource.Success -> it.data?.steps?.let { step -> stepAdapter.setAllData(step) }
            }
        })
    }
}