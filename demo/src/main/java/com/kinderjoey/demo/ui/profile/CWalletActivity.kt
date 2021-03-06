package com.kinderjoey.demo.ui.profile

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.kinderjoey.demo.R
import com.kinderjoey.demo.data.DataDummy
import com.kinderjoey.demo.databinding.ActivityCwalletBinding

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
        /*binding.apply {
            rvLastTransaction.apply {
                adapter = transactionAdapter
                layoutManager = LinearLayoutManager(this@CWalletActivity, LinearLayoutManager.VERTICAL, false)
                isNestedScrollingEnabled = false
            }
            includeAppBarMiddle.apply {
                tvTittle.text = "Cookiez Wallet"
                ivFavorite.visibility = View.GONE
            }
        }*/

        transactionAdapter.setAllData(DataDummy.setTransaction())
    }
}