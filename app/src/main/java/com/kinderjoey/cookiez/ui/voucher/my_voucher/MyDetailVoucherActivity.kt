package com.kinderjoey.cookiez.ui.voucher.my_voucher

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.ActivityMyDetailVoucherBinding
import com.kinderjoey.cookiez.model.Voucher
import com.kinderjoey.cookiez.ui.detail.order.DetailOrderMenuFragment
import com.kinderjoey.cookiez.ui.voucher.VoucherViewModel

class MyDetailVoucherActivity : AppCompatActivity() {
    companion object {
        const val VOUCHER = "voucher"
    }
    private lateinit var binding: ActivityMyDetailVoucherBinding
    private val viewModel: VoucherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyDetailVoucherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
        }
        val intentValue = intent.getParcelableExtra<Voucher>(VOUCHER)
        with(binding) {
            intentValue?.let { item ->
                Log.d("CEKK","isi item $item")
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

            btnUseVoucher.setOnClickListener {
                onBackPressed()
                DetailOrderMenuFragment.precentageDiscount = intentValue?.voucherDiscount!!
                Toast.makeText(this@MyDetailVoucherActivity, "Silahkan gunakan voucher untuk pemesanan",Toast.LENGTH_LONG).show()
            }


        }
    }
}