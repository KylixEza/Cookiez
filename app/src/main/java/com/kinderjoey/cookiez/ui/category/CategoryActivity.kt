package com.kinderjoey.cookiez.ui.category

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.adapter.SelectedCategoryAdapter
import com.kinderjoey.cookiez.databinding.ActivityCategoryBinding
import com.kinderjoey.cookiez.util.Constanta

class CategoryActivity : AppCompatActivity() {
    private val binding by viewBinding<ActivityCategoryBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
        }

        val categoryAdapter = SelectedCategoryAdapter(this)
        val categoryTitle = intent.getStringExtra(Constanta.KEY_OF_CATEGORY)

        binding.apply {
            includeAppBarMiddle.apply {
                ivFavorite.visibility = View.GONE
                tvTittle.text = categoryTitle
            }
            rvSelectedCategory.apply {
                adapter = categoryAdapter
                layoutManager = LinearLayoutManager(this@CategoryActivity, LinearLayoutManager.VERTICAL, false)
            }
        }
    }
}