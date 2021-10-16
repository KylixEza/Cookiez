package com.kinderjoey.cookiez.ui.profile

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.kinderjoey.cookiez.R
import com.kinderjoey.cookiez.databinding.FragmentProfileBinding
import com.kinderjoey.cookiez.model.User
import com.kinderjoey.cookiez.ui.history.HistoryActivity
import com.kinderjoey.cookiez.ui.profile.wallet.CWalletActivity

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val viewModel: ProfileViewModel by activityViewModels()
    private val uid = firebaseAuth.currentUser?.uid
    var photoMax: Int = 1
    var photoLocation: Uri? = null
    lateinit var storageRef: StorageReference
    var profilePic: String = ""

    private fun getFileExtension(uri: Uri?): String? {
        val contentResolver = activity?.contentResolver
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
            Glide.with(requireContext())
                .load(photoLocation)
                .into(binding.imgProfile)
            uploadImage()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCookiezWallet.setOnClickListener {
            startActivity(Intent(requireActivity(), CWalletActivity::class.java))
        }
        binding.btnHistory.setOnClickListener {
            startActivity(Intent(requireActivity(), HistoryActivity::class.java))
        }
        Log.d("CEKK","uid $uid")
        uid?.let { viewModel.getUser(it).observe(viewLifecycleOwner, ::setProfile) }
    }

    @SuppressLint("SetTextI18n")
    private fun setProfile(user: User) {
        user.let { data ->
            Log.d("CEKK","user $user")
        with(binding){

            tvName.text = data.name
            tvEmail.text = data.email
            tvCoin.text = data.coin.toString()+" Coin"
            tvXp.text = data.xp.toString()+" XP"

            Glide.with(requireContext())
                .load(data.avatar)
                .placeholder(R.drawable.ilu_default_profile_picture)
                .apply(RequestOptions())
                .into(imgProfile)

        }

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



}