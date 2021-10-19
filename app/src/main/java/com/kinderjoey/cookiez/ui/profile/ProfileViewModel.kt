package com.kinderjoey.cookiez.ui.profile

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.kinderjoey.cookiez.data.sources.datastore.DataStorePlayground
import com.kinderjoey.cookiez.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfileViewModel(application: Application): AndroidViewModel(application){

    private val datastore = DataStorePlayground.getInstance(application)

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
        val userCollection =Firebase.firestore.collection("user")
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = userCollection
                .whereEqualTo("id",uid)
                .get()
                .await()
            for (document in querySnapshot) {
                Log.d("ProfileViewModel","$uid $profilePic")
                val userRef = userCollection.document(document.id)
                userRef.update("avatar", profilePic)
            }
        }
    }

    fun updateUser(uid: String, username: String, phoneNumber: String) {
        val storyCollectionRef = Firebase.firestore.collection("user")
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = storyCollectionRef
                .whereEqualTo("id", uid)
                .get()
                .await()
            for (document in querySnapshot) {
                val userRef = storyCollectionRef.document(document.id)
                userRef.update("name", username)
                userRef.update("phone", phoneNumber)

            }
        }
    }

    fun updateAdress(uid:String, address: String) {
        val storyCollectionRef = Firebase.firestore.collection("user")
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = storyCollectionRef
                .whereEqualTo("id", uid)
                .get()
                .await()
            for (document in querySnapshot) {
                val userRef = storyCollectionRef.document(document.id)
                userRef.update("address", address)
            }
        }
    }

    fun savePrefEmail() = viewModelScope.launch {
        datastore.savePrefEmail("null")
    }

}