package com.kinderjoey.demo.ui.history

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import com.bumptech.glide.Glide
import com.kinderjoey.demo.R
import com.kinderjoey.demo.databinding.ActivityHistoryBinding
import com.kinderjoey.demo.ui.MainActivity

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
        }

        binding.includeAppBarMiddle.apply {
            tvTittle.text = "Riwayat Pesanan"
            ivFavorite.visibility = View.GONE
        }

        binding.apply {
            includeHistory1.apply {
                Glide.with(this@HistoryActivity)
                    .load(resources.getDrawable(R.drawable.nasi_goreng_asia))
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
                    .load(resources.getDrawable(R.drawable.nasi_goreng_asia))
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
                    .load(resources.getDrawable(R.drawable.nasi_goreng_asia))
                    .centerCrop()
                    .into(ivImgMenu)
                cvStatus.setBackgroundColor(resources.getColor(R.color.card_on_canceled))
                tvStatus.text = "Di batalkan"
                btnDecision.visibility = View.GONE
                includeSendRating.cvContainerRating.visibility = View.GONE
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }
}