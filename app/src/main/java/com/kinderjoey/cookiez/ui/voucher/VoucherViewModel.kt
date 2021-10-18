package com.kinderjoey.cookiez.ui.voucher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.kinderjoey.cookiez.model.UserVoucherModel
import com.kinderjoey.cookiez.model.Voucher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class VoucherViewModel : ViewModel() {

/*    suspend fun getUser(uid: String): UserModel {
        var user = UserModel()
        val getData = CoroutineScope(Dispatchers.IO).launch {
            delay(2000L)
            val querySnapshot = Firebase.firestore.collection("users")
                .whereEqualTo("uid", uid)
                .get()
                .await()
            for (users in querySnapshot.documents) {
                users.toObject<UserModel>()?.also {
                    user = it
                }
            }
        }
        getData.join()
        return user
    }
    suspend fun getUserKoin(uid: String): Int {
        var user = UserModel()
        val getData = CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = Firebase.firestore.collection("users")
                .whereEqualTo("uid", uid)
                .get()
                .await()
            for (users in querySnapshot.documents) {
                users.toObject<UserModel>()?.also {
                    user = it
                }
            }
        }
        getData.join()
        return user.point
    }*/

    fun getListVoucher(): LiveData<ArrayList<Voucher>> {
        val _listVoucher = MutableLiveData<ArrayList<Voucher>>()
        val listVoucher = ArrayList<Voucher>()
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = Firebase.firestore.collection("listVoucher")
                .get()
                .await()
            for (kupon in querySnapshot.documents) {
                kupon.toObject<Voucher>().also {
                    listVoucher.add(it!!)
                }
            }
            listVoucher.sortBy { it.coin }
            _listVoucher.postValue(listVoucher)
        }

        return _listVoucher
    }

    suspend fun getUserTiket(uid: String): ArrayList<Voucher> {
        val listKupon = ArrayList<Voucher>()
        var kuponUser = UserVoucherModel()
            val getData = CoroutineScope(Dispatchers.IO).launch {
            val kuponUserSnapshot = Firebase.firestore.collection("kuponUser")
                .whereEqualTo("uid", uid)
                .get()
                .await()
            for (kupon in kuponUserSnapshot.documents) {
                kupon.toObject<UserVoucherModel>().also {
                    if (it != null) {
                        kuponUser = it
                    }
                }
            }

            kuponUser.listKupon?.let { kuponId ->
                for (i in kuponId) {
                    val querySnapshot = Firebase.firestore.collection("listKupon")
                        .whereEqualTo("kuponId", i)
                        .get()
                        .await()
                    for (kupon in querySnapshot.documents) {
                        kupon.toObject<Voucher>().also {
                            listKupon.add(it!!)
                        }
                    }
                    listKupon.sortBy { it.coin }

                }
            }
        }
        getData.join()
        return listKupon
    }




    fun addToUserKupon(kuponId: String, uid: String, coin: Int) {
        val list = ArrayList<String>()
        list.add(kuponId)
        val historyData = UserVoucherModel(uid, list)
        val ref = FirebaseFirestore.getInstance().collection("kuponUser")
        val data = ref.document(uid)
        val query = ref.whereEqualTo("uid", uid)
        query.addSnapshotListener { document, _ ->
            if (document != null) {
                if (document.size() > 0) {
                    data.update("listKupon", FieldValue.arrayUnion(kuponId))
                } else {
                    data.set(historyData)
                }

            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = Firebase.firestore.collection("users")
            val userDoc = querySnapshot
                .whereEqualTo("uid", uid)
                .get()
                .await()
            for (user in userDoc) {
                val userRef = querySnapshot.document(user.id)
                var decreaseCoin = 0.0
                decreaseCoin -= coin
                userRef.update("point", FieldValue.increment(decreaseCoin))
            }
        }
    }
}