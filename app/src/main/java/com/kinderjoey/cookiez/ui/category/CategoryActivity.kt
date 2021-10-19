package com.kinderjoey.cookiez.ui.category

import android.content.Intent
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
import com.kinderjoey.cookiez.model.menu.Menu
import com.kinderjoey.cookiez.ui.BaseActivity
import com.kinderjoey.cookiez.util.CategoryType
import org.koin.android.viewmodel.ext.android.viewModel

class CategoryActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityCategoryBinding>()
    private val categoryViewModel by viewModel<CategoryViewModel>()
    private lateinit var categoryAdapter: SelectedCategoryAdapter
    private val args by navArgs<CategoryActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
        }

        categoryAdapter = SelectedCategoryAdapter()
        val categoryTitle = args.categoryTitle
        val categoryType = args.categoryType

        binding.apply {
            includeAppBarMiddle.apply {
                ivFavorite.visibility = View.GONE
                tvTittle.text = categoryTitle
                ivArrowBack.setOnClickListener {
                    startActivity(Intent(this@CategoryActivity, BaseActivity::class.java))
                    finish()
                }
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
                    is Resource.Empty -> emptyCondition()
                    is Resource.Error -> errorCondition(it.message)
                    is Resource.Loading -> loadingCondition()
                    is Resource.Success -> it.data?.let { menu -> successCondition(menu) }
                }
            })
        } else {
            categoryViewModel.getCategoryMenus(category).observe(this@CategoryActivity, {
                when(it) {
                    is Resource.Empty -> emptyCondition()
                    is Resource.Error -> errorCondition(it.message)
                    is Resource.Loading -> loadingCondition()
                    is Resource.Success -> it.data?.let { menu -> successCondition(menu) }
                }
            })
        }
    }

    private fun emptyCondition() {
        binding.apply {
            tvErrorSelectedCategory.text = "Tidak ada menu terkait"
            progressSelectedCategory.visibility = View.INVISIBLE
        }
    }

    private fun errorCondition(e: String?) {
        binding.apply {
            tvErrorSelectedCategory.text = e
            progressSelectedCategory.visibility = View.INVISIBLE
        }
    }

    private fun loadingCondition() {
        binding.progressSelectedCategory.visibility = View.VISIBLE
    }

    private fun successCondition(data: List<Menu>) {
        binding.apply {
            categoryAdapter.setAllData(data)
            binding.progressSelectedCategory.visibility = View.INVISIBLE
        }
    }
}