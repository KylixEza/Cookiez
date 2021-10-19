package com.kinderjoey.cookiez.ui.notification

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.ActivityNotificationBinding
import com.kinderjoey.cookiez.ui.BaseActivity

class NotificationActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityNotificationBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
        }

        binding.includeAppBarNotification.apply {
            tvTittle.text = String.format("Notifikasi")
            ivFavorite.visibility = View.GONE
            ivArrowBack.setOnClickListener {
                startActivity(Intent(this@NotificationActivity, BaseActivity::class.java))
            }
        }
    }
}