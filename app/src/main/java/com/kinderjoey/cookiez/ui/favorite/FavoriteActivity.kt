package com.kinderjoey.cookiez.ui.favorite

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.adapter.FavoriteAdapter
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.databinding.ActivityFavoriteBinding
import com.kinderjoey.cookiez.ui.BaseActivity
import com.kinderjoey.cookiez.ui.profile.ProfileViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    private val viewModel by viewModel<FavoriteViewModel>()
    private val binding by viewBinding<ActivityFavoriteBinding>()
    private lateinit var favoriteAdapter: FavoriteAdapter
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val uid = firebaseAuth.currentUser?.uid
    private var id = ""
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
        }

        favoriteAdapter = FavoriteAdapter()

        binding.apply {
            includeAppBarFavorite.apply {
                tvTittle.text = String.format("Menu Favorit")
                ivFavorite.visibility = View.INVISIBLE
                ivArrowBack.setOnClickListener {
                    onBackPressed()
                }
            }
            rvFavorite.apply {
                adapter = favoriteAdapter
                layoutManager =
                    LinearLayoutManager(this@FavoriteActivity, LinearLayoutManager.VERTICAL, false)
            }
        }

        uid?.let {
            profileViewModel.getUser(it).observe(this@FavoriteActivity, { user ->
                id = user.id
                observeListFavorite()
            })
        }
    }

    private fun observeListFavorite() {
        viewModel.getAllFavorites(id).observe(this@FavoriteActivity, {
            when(it) {
                is Resource.Empty -> {
                    binding.progressFavorite.visibility = View.INVISIBLE
                    binding.includeEmptyFavorite.apply {
                        ivImgEmptyFavorite.visibility = View.VISIBLE
                        tvErrorFavorite.visibility = View.VISIBLE
                    }
                }
                is Resource.Error -> {
                    binding.progressFavorite.visibility = View.INVISIBLE
                    binding.includeEmptyFavorite.apply {
                        ivImgEmptyFavorite.visibility = View.VISIBLE
                        tvErrorFavorite.apply {
                            visibility = View.VISIBLE
                            text = it.message
                        }
                    }
                }
                is Resource.Loading -> binding.progressFavorite.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressFavorite.visibility = View.INVISIBLE
                    it.data?.let { data
                        -> favoriteAdapter.setAllData(data) }
                }
            }
        })
    }
}