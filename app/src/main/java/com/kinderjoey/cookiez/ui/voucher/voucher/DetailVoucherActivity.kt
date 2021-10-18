package com.kinderjoey.cookiez.ui.voucher.voucher

import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.ActivityDetailVoucherBinding
import com.kinderjoey.cookiez.model.Voucher
import com.kinderjoey.cookiez.ui.voucher.VoucherViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailVoucherActivity : AppCompatActivity() {
    companion object {
        const val VOUCHER = "voucher"
    }
    private val uid = FirebaseAuth.getInstance().currentUser?.uid
    private lateinit var binding: ActivityDetailVoucherBinding
    private val viewModel: VoucherViewModel by viewModels()

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

                uid?.let { uid ->
                    CoroutineScope(Dispatchers.IO).launch {
                        val currUser =  viewModel.getUserKoin(uid)
                        withContext(Dispatchers.Main){
                            btnUseVoucher.setOnClickListener { _ ->
                                val dialog = AlertDialog.Builder(this@DetailVoucherActivity)
                                dialog.setTitle("Pembelian Kupon")
                                dialog.setMessage("Apakah kamu yakin ingin membeli kupon ini?")
                                dialog.setPositiveButton("Iya") { dialog: DialogInterface?, which: Int ->
                                    if (currUser > item.coin) {
                                        viewModel.addToUserKupon(item.uid, uid,item.coin)
                                        Toast.makeText(this@DetailVoucherActivity,"Pembelian vouchermu berhasil",Toast.LENGTH_SHORT).show()
                                        onBackPressed()
                                    } else {
                                        Toast.makeText(
                                            this@DetailVoucherActivity,
                                            "Maaf Coin milikmu belum mencukupi",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                                dialog.setNegativeButton("Tidak") { dialog: DialogInterface?, which: Int -> }
                                dialog.show()
                            }

                        }
                    }
                }

            }

            ivArrowBack.setOnClickListener {
                onBackPressed()
            }



        }
    }
}