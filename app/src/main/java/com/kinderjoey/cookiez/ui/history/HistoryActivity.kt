package com.kinderjoey.cookiez.ui.history

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import com.bumptech.glide.Glide
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.ActivityFavoriteBinding
import com.kinderjoey.cookiez.databinding.ActivityHistoryBinding
import com.kinderjoey.cookiez.ui.BaseActivity

class HistoryActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityHistoryBinding>()

    companion object {
        const val URL = "https://cdn-2.tstatic.net/tribunnews/foto/bank/images/cara-masak-nasi-goreng" +
            "-sederhana-tapi-enak-banget-ala-pedagang-gerobak.jpg"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
        }

        binding.includeAppBarMiddle.apply {
            tvTittle.text = String.format("Riwayat Pesanan")
            ivFavorite.visibility = View.GONE
            ivArrowBack.setOnClickListener {
                startActivity(Intent(this@HistoryActivity, BaseActivity::class.java))
            }
        }

        binding.apply {
            includeHistory1.apply {
                Glide.with(this@HistoryActivity)
                    .load(URL)
                    .centerCrop()
                    .into(ivImgMenu)
                cvStatus.setBackgroundColor(resources.getColor(R.color.card_on_process))
                tvStatus.text = "Di Proses"
                btnDecision.apply {
                    setBackgroundColor(resources.getColor(R.color.card_on_order_cancel))
                    text = "Batalkan Pesanan"
                }
                includeSendRating.cvContainerRating.visibility = View.GONE
            }
            includeHistory2.apply {
                Glide.with(this@HistoryActivity)
                    .load(URL)
                    .centerCrop()
                    .into(ivImgMenu)
                cvStatus.setBackgroundColor(resources.getColor(R.color.card_on_success))
                tvStatus.text = "Selesai"
                btnDecision.apply {
                    setBackgroundColor(resources.getColor(R.color.card_on_success))
                    text = "Pesan lagi yuk!"
                }
            }
            includeHistory3.apply {
                Glide.with(this@HistoryActivity)
                    .load(URL)
                    .centerCrop()
                    .into(ivImgMenu)
                cvStatus.setBackgroundColor(resources.getColor(R.color.card_on_canceled))
                tvStatus.text = "Di batalkan"
                btnDecision.visibility = View.GONE
                includeSendRating.cvContainerRating.visibility = View.GONE
            }
        }
    }
}