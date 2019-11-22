package com.example.lstmanager.DAO

import android.util.Log
import com.example.lstmanager.objects.Commitment
import com.example.lstmanager.objects.Employee
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class FirebaseDAO{
    private val db = FirebaseFirestore.getInstance()

    fun getEmployee(code: String, callback: (Employee?, ArrayList<Commitment>) -> Unit){
        val employeeRef = db.collection("employee")
        employeeRef.whereEqualTo("chip", code)
            .get()
            .addOnSuccessListener { documents ->
                val TAG = "QUERY"
                var employee: Employee? = null
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    employee = Employee(document.id, document.data["name"].toString(), document.data["surname"].toString())
                }
                getCommitments(employee, callback)
            }
    }

    private fun getCommitments(employee: Employee?, callback: (Employee?, ArrayList<Commitment>) -> Unit){
        val commitmentsRef = db.collection("commesse")
        commitmentsRef.get()
            .addOnSuccessListener { result ->
                val TAG = "COMMESSE"
                val commitments = ArrayList<Commitment>()
                for (document in result) {
                    commitments.add(Commitment(document.id, document.data["name"].toString(), document.data["number"].toString()))
                }
                Log.d(TAG, commitments.toString())
                callback(employee, commitments)
            }
    }
}