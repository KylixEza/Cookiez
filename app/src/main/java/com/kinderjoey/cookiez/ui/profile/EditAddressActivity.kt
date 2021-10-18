package com.kinderjoey.cookiez.ui.profile

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.ActivityEditAddressBinding

class EditAddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditAddressBinding
    private val viewModel: ProfileViewModel by viewModels()
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val uid = firebaseAuth.currentUser?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAddressBinding.inflate(layoutInflater)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
        }

        setContentView(binding.root)


        binding.ivArrowBack.setOnClickListener {
            onBackPressed()
        }

        binding.edtAddress.editText?.setText(intent.getStringExtra("ADDRESS") as String)

        binding.btnUpdate.setOnClickListener {

            if (uid != null) {
                with(binding){
                    val address = edtAddress.editText?.text.toString()
                    viewModel.updateAdress(uid,address).also {
                        loadingState(true)
                        onBackPressed()
                    }


                }

            }
        }
    }
    private fun loadingState(state: Boolean) {
        with(binding){
            progressBar.isVisible = state
            btnUpdate.isEnabled = !state
        }
    }
}