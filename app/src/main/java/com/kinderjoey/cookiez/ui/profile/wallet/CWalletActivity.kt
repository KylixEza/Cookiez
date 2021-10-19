package com.kinderjoey.cookiez.ui.profile.wallet

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.adapter.PreviewTransactionAdapter
import com.kinderjoey.cookiez.data.sources.dummy.DataDummy
import com.kinderjoey.cookiez.databinding.ActivityCwalletBinding

class CWalletActivity : AppCompatActivity() {
    private val binding by viewBinding<ActivityCwalletBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cwallet)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
        }

        val transactionAdapter = PreviewTransactionAdapter()
        binding.apply {
            rvLastTransaction.apply {
                transactionAdapter.setAllData(DataDummy.setTransaction())
                adapter = transactionAdapter
                layoutManager = LinearLayoutManager(this@CWalletActivity, LinearLayoutManager.VERTICAL, false)
                isNestedScrollingEnabled = false
            }


            btnTopUp.setOnClickListener {
                startActivity(Intent(this@CWalletActivity,TopUpActivity::class.java))
            }
            binding.ivArrowBack.setOnClickListener {
                onBackPressed()
            }

        }


    }
}