package com.example.lstmanager

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.*
import kotlin.collections.HashMap

class FirebaseDAO{
    private val db = FirebaseFirestore.getInstance()

    fun getEmployee(code: String?, callback: (HashMap<String, String>) -> Unit){
        val employeeRef = db.collection("employee")
        val employee = HashMap<String, String>()
        employeeRef.whereEqualTo("chip", code)
            .get()
            .addOnSuccessListener { documents ->
                val TAG = "QUERY"
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    for (elem in document.data){
                        employee[elem.key.toString()] = elem.value.toString()
                    }
                }
                callback(employee)
            }
    }
}