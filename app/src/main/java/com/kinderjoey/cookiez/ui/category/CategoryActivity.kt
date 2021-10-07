package com.kinderjoey.cookiez.ui.category

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.adapter.SelectedCategoryAdapter
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.databinding.ActivityCategoryBinding
import com.kinderjoey.cookiez.util.CategoryType
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class CategoryActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityCategoryBinding>()
    private val categoryViewModel by viewModel<CategoryViewModel>()
    private val categoryAdapter by inject<SelectedCategoryAdapter>()
    private val args by navArgs<CategoryActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
        }

        val categoryTitle = args.categoryTitle
        val categoryType = args.categoryType

        binding.apply {
            includeAppBarMiddle.apply {
                ivFavorite.visibility = View.GONE
                tvTittle.text = categoryTitle
            }
            rvSelectedCategory.apply {
                adapter = categoryAdapter
                layoutManager = LinearLayoutManager(this@CategoryActivity, LinearLayoutManager.VERTICAL, false)
                observeCategory(categoryType)
            }
        }
    }

    private fun observeCategory(category: String) {

        if (category == CategoryType.All.type) {
            categoryViewModel.getAllMenus().observe(this@CategoryActivity, {
                when(it) {
                    is Resource.Empty -> {}
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> it.data?.let { data -> categoryAdapter.setAllData(data) }
                }
            })
        } else {
            categoryViewModel.getCategoryMenus(category).observe(this@CategoryActivity, {
                when(it) {
                    is Resource.Empty -> {}
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> it.data?.let { data -> categoryAdapter.setAllData(data) }
                }
            })
        }


    }
}