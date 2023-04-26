package com.example.talks

import android.content.Context
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DAO {

    val fDatabase = FirebaseFirestore.getInstance()
    val userColl = fDatabase.collection("users")
    fun add(user: User?, context: Context){
        if(user!=null){
            GlobalScope.launch(Dispatchers.IO) {
                fDatabase.collection("user").add(user)
                    .addOnSuccessListener { docc->
                        Toast.makeText(context, "added succussfully in firestore", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener{
                        Toast.makeText(context, "could not add data", Toast.LENGTH_LONG).show()
                    }
            }
        }
    }
    fun getCurrentUser(uid: String): Task<DocumentSnapshot> {
        return userColl.document(uid).get()
    }
}