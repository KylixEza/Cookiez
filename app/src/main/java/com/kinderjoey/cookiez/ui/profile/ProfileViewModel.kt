package com.kinderjoey.cookiez.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.kinderjoey.cookiez.data.repository.IProfileRepository
import com.kinderjoey.cookiez.data.sources.firestore.network.FirebaseResponse
import com.kinderjoey.cookiez.data.sources.firestore.response.UserResponse
import com.kinderjoey.cookiez.data.util.Resource
import com.kinderjoey.cookiez.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfileViewModel():ViewModel() {

    fun getUser(uid: String): LiveData<User> {
        val user = MutableLiveData<User>()
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = Firebase.firestore.collection("user")
                .whereEqualTo("id",uid)
                .get()
                .await()
            for(users in querySnapshot.documents){
                users.toObject<User>().also {
                    user.postValue(it)
                }
            }
        }
        return user
    }

    fun changeProfileImage(uid: String, profilePic: String) {
        val userCollection =Firebase.firestore.collection("users")
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = userCollection
                .whereEqualTo("uid",uid)
                .get()
                .await()
            for (document in querySnapshot) {
                Log.d("ProfileViewModel","$uid $profilePic")
                val userRef = userCollection.document(document.id)
                userRef.update("avatar", profilePic)
            }
        }
    }
    fun updateUser(user: User) {
        val storyCollectionRef = Firebase.firestore.collection("users")
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = storyCollectionRef
                .whereEqualTo("email", user.email)
                .whereEqualTo("uid", user.id)
                .get()
                .await()
            for (document in querySnapshot) {
                val userRef = storyCollectionRef.document(document.id)
                userRef.update("address", user.address)
                userRef.update("name", user.name)
                userRef.update("phone", user.phoneNumber)

            }
        }

    }




}