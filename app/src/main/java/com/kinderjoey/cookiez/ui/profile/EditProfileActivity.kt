package com.kinderjoey.cookiez.ui.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.MimeTypeMap
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val uid = firebaseAuth.currentUser?.uid


    override fun onCreate(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = resources.getColor(R.color.white)
        }

        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            binding.edtName.editText?.setText(intent.getStringExtra("USERNAME") as String)
            binding.edtPhoneNumber.editText?.setText(intent.getStringExtra("PHONE_NUMBER") as String)
            Glide.with(this@EditProfileActivity)
                .load(intent.getStringExtra("AVATAR"))
                .placeholder(R.drawable.ic_ava)
                .into(binding.ivProfile)
        }

        binding.btnChangeProfilePicture.setOnClickListener {
            findPhoto()
        }
        binding.ivArrowBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnUpdateProfile.setOnClickListener {

            if (uid != null) {
                with(binding){
                    val username = edtName.editText?.text.toString()
                    val phoneNumber = edtPhoneNumber.editText?.text.toString()
                    viewModel.updateUser(uid,username,phoneNumber).also {
                        loadingState(true)
                        onBackPressed()
                    }


                }

            }
        }
    }

    private var photoMax: Int = 1
    private var photoLocation: Uri? = null
    private lateinit var storageRef: StorageReference
    private var profilePic: String = ""

    private fun getFileExtension(uri: Uri?): String? {
        val contentResolver = contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(contentResolver?.getType(uri!!))
    }

    private fun findPhoto() {
        val pic = Intent()
        pic.type = "image/*"
        pic.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(pic, photoMax)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == photoMax && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            photoLocation = data.data
            Glide.with(this@EditProfileActivity)
                .load(photoLocation)
                .into(binding.ivProfile)
            uploadImage()
        }
    }

    private fun uploadImage() {
        if (photoLocation != null) {
            storageRef = FirebaseStorage.getInstance().reference.child("AvatarPath")
            val storage = storageRef.child(
                System.currentTimeMillis()
                    .toString() + "." + getFileExtension(photoLocation)
            )
            photoLocation?.let { it1 ->
                storage.putFile(it1).addOnSuccessListener {
                    storage.downloadUrl.addOnSuccessListener(OnSuccessListener<Uri> { uri ->
                        profilePic = uri.toString()
                        uid?.let { id -> viewModel.changeProfileImage(id,  profilePic) }
                    })
                }
            }
        }
    }
    private fun loadingState(state: Boolean) {
        with(binding){
            progressBar.isVisible = state
            btnUpdateProfile.isEnabled = !state
        }
    }
}