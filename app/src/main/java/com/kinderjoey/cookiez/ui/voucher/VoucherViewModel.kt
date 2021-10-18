package com.kinderjoey.cookiez.ui.voucher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.kinderjoey.cookiez.model.User
import com.kinderjoey.cookiez.model.UserVoucherModel
import com.kinderjoey.cookiez.model.Voucher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class VoucherViewModel : ViewModel() {

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

    suspend fun getUserVoucher(uid: String): ArrayList<Voucher> {
        val listVoucher = ArrayList<Voucher>()
        var voucherUser = UserVoucherModel()
            val getData = CoroutineScope(Dispatchers.IO).launch {
            val kuponUserSnapshot = Firebase.firestore.collection("voucherUser")
                .whereEqualTo("uid", uid)
                .get()
                .await()
            for (kupon in kuponUserSnapshot.documents) {
                kupon.toObject<UserVoucherModel>().also {
                    if (it != null) {
                        voucherUser = it
                    }
                }
            }

            voucherUser.listVoucher?.let { voucherId ->
                for (i in voucherId) {
                    val querySnapshot = Firebase.firestore.collection("listVoucher")
                        .whereEqualTo("uid", i)
                        .get()
                        .await()
                    for (kupon in querySnapshot.documents) {
                        kupon.toObject<Voucher>().also {
                            listVoucher.add(it!!)
                        }
                    }
                    listVoucher.sortBy { it.coin }

                }
            }
        }
        getData.join()
        return listVoucher
    }



    suspend fun getUserKoin(uid: String): Int {
        var user = User()
        val getData = CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = Firebase.firestore.collection("user")
                .whereEqualTo("id", uid)
                .get()
                .await()
            for (users in querySnapshot.documents) {
                users.toObject<User>()?.also {
                    user = it
                }
            }
        }
        getData.join()
        return user.coin
    }

    fun addToUserKupon(voucherId: String, uid: String, coin: Int) {
        val list = ArrayList<String>()
        list.add(voucherId)
        val historyData = UserVoucherModel(uid, list)
        val ref = FirebaseFirestore.getInstance().collection("voucherUser")
        val data = ref.document(uid)
        val query = ref.whereEqualTo("uid", uid)
        query.addSnapshotListener { document, _ ->
            if (document != null) {
                if (document.size() > 0) {
                    data.update("listVoucher", FieldValue.arrayUnion(voucherId))
                } else {
                    data.set(historyData)
                }

            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = Firebase.firestore.collection("user")
            val userDoc = querySnapshot
                .whereEqualTo("id", uid)
                .get()
                .await()
            for (user in userDoc) {
                val userRef = querySnapshot.document(user.id)
                var decreaseCoin = 0.0
                decreaseCoin -= coin
                userRef.update("coin", FieldValue.increment(decreaseCoin))
            }
        }
    }
}