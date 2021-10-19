package com.kinderjoey.cookiez.ui.profile.wallet

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.ActivityDetailTopUpBinding

class DetailTopUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTopUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTopUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
        }
        val name = intent.getStringExtra("TopUpEntity")


        val listDesc = listOf(
            "Masukkan kartu ATM dan PIN $name ",
            "Pilih Transaksi Lain > Pembayaran >  Lainnya",
            "Pilih $name Virtual Account ",
            "Masukkan angka 99999 diikuti nomor HP-mu (mis : 9999 082x xxxx xxxx)",
            "Masukkan nominal isi ulang. Bankmu mungkin mengenakan biaya isi ulang yang terpisah",
            " Ikuti instruksi untuk menyelesaikan transaksi isi ulang saldo"
        )
        var count = 1
        var desc = ""
        for (i in listDesc) {
            desc += "$count. $i \n"
            count++
        }
        binding.tvDesc.text = desc
        binding.tvTittle.text =name
        binding.ivArrowBack.setOnClickListener {
            onBackPressed()
        }
    }
}