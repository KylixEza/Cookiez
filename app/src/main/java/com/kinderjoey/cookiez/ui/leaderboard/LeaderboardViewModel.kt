package com.kinderjoey.cookiez.ui.leaderboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.kinderjoey.cookiez.model.User
import com.kinderjoey.cookiez.model.UserLeaderBoard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LeaderboardViewModel : ViewModel() {

    fun getLeaderboard(uid: String): Pair<LiveData<ArrayList<UserLeaderBoard>>, LiveData<UserLeaderBoard>> {
        val listLeaderBoard = MutableLiveData<ArrayList<UserLeaderBoard>>()
        val currentUser = MutableLiveData<UserLeaderBoard>()
        val tempList = ArrayList<UserLeaderBoard>()
        var user= User("")
        val listUser = ArrayList<User>()
        CoroutineScope(Dispatchers.IO).launch {
            val getUser = CoroutineScope(Dispatchers.IO).launch {
                val querySnapshot = Firebase.firestore.collection("user")
                    .get()
                    .await()
                for (document in querySnapshot.documents) {
                    document.toObject<User>().also {
                        if (it != null) {
                            if (it.id == uid) {
                                user = it
                                listUser.add(it)
                            } else {
                                listUser.add(it)
                            }

                        }
                    }
                }
            }
            getUser.join()

            listUser.sortByDescending { it.xp }
            var count = 1
            for (i in listUser) {
                tempList.add(
                    UserLeaderBoard(count, i.avatar, i.name, i.xp)
                )
                count++
            }
            for (i in tempList){
               if( i.username == user.name){
                   currentUser.postValue(i)
               }
            }
            listLeaderBoard.postValue(tempList)
        }
        return Pair(listLeaderBoard,currentUser)
    }
}