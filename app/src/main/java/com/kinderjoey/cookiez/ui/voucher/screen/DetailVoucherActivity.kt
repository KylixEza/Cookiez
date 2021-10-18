package com.kinderjoey.cookiez.ui.voucher.screen

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.ActivityDetailVoucherBinding
import com.kinderjoey.cookiez.model.Voucher

class DetailVoucherActivity : AppCompatActivity() {
    companion object {
        const val VOUCHER = "voucher"
    }

    private lateinit var binding: ActivityDetailVoucherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailVoucherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
        }
        val intentValue = intent.getParcelableExtra<Voucher>(VOUCHER)
        with(binding) {
            intentValue?.let { item ->

                tvValidUntil.text = item.validUntil
                tvVoucherCategory.text = item.voucherCategory
                tvVoucherDiscount.text = "Discount ${item.voucherDiscount}%"
                when (item.background) {
                    "bg_voucher1" -> constraintLayout.setBackgroundResource(R.drawable.bg_voucher1)
                    "bg_voucher2" -> constraintLayout.setBackgroundResource(R.drawable.bg_voucher2)
                    "bg_voucher3" -> constraintLayout.setBackgroundResource(R.drawable.bg_voucher3)
                }

                val listSnK = listOf(
                    "Dapatkan potongan ${item.voucherDiscount}% dari harga bahan makanan pada halaman tutorial",
                    "Voucher potongan ${item.voucherDiscount}% bernilai hingga maksimal Rp20.000 (Dua puluh ribu rupiah)",
                    "Berlaku untuk semua tutorial memasak yang terdapat di Cookiez",
                    "Voucher hanya akan berlaku untuk 1 (satu) pengguna ",
                    "Pengguna diharapkan memperhatikan tanggal kadaluarsa dari voucher yang akan ditukarkan",
                    "Voucher dapat digunakan pada halaman detail pemesanan pada saat dilakukan pemesanan"
                )
                var count = 1
                var snk = ""
                for (i in listSnK) {
                    snk += "$count. $i \n"
                    count++
                }
                tvSdk.text = snk

            }

            ivArrowBack.setOnClickListener {
                onBackPressed()
            }
        }
    }
}