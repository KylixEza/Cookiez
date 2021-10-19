package com.kinderjoey.cookiez.ui.profile.wallet

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.adapter.PreviewTransactionAdapter
import com.kinderjoey.cookiez.adapter.TopUpAdapter
import com.kinderjoey.cookiez.data.sources.dummy.DataDummy
import com.kinderjoey.cookiez.databinding.ActivityTopUpBinding

class TopUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTopUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
        }
       val topUpAdapter = TopUpAdapter()
        binding.apply {
            rvTopUp.apply {
                adapter = topUpAdapter
                topUpAdapter.setAllData(DataDummy.setTopUpPayment())
                layoutManager =
                    LinearLayoutManager(this@TopUpActivity, LinearLayoutManager.VERTICAL, false)
                isNestedScrollingEnabled = false
            }
        }
        binding.ivArrowBack.setOnClickListener {
            onBackPressed()
        }
    }
}