package com.example.lstmanager.DAO

import android.content.Context
import android.util.Log
import com.example.lstmanager.R
import com.example.lstmanager.objects.Commitment
import com.example.lstmanager.objects.Employee
import com.example.lstmanager.objects.Job
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.Timestamp
import kotlin.collections.ArrayList

class FirebaseDAO{
    private val db = FirebaseFirestore.getInstance()

    fun getEmployee(context: Context, code: String, callback: (Employee?, ArrayList<Commitment>) -> Unit){
        val employeeRef = db.collection(context.getString(R.string.collection_employee))
        employeeRef.whereEqualTo("chip", code)
            .get()
            .addOnSuccessListener { documents ->
                val TAG = "QUERY"
                var employee: Employee? = null
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    if (document.data["runningJobs"] == null) {
                        employee = Employee(
                            document.id,
                            document.data["name"].toString(),
                            document.data["surname"].toString(),
                            ArrayList<String>()
                        )
                    } else {
                        employee = Employee(
                            document.id,
                            document.data["name"].toString(),
                            document.data["surname"].toString(),
                            document.data["runningJobs"]!! as ArrayList<String>
                        )
                    }
                }
                getCommitments(context, employee, callback)
            }
    }

    private fun getCommitments(context: Context, employee: Employee?, callback: (Employee?, ArrayList<Commitment>) -> Unit){
        val commitmentsRef = db.collection(context.getString(R.string.collection_commesse))
        commitmentsRef.get()
            .addOnSuccessListener { result ->
                val TAG = "COMMESSE"
                val commitments = ArrayList<Commitment>()
                for (document in result) {
                    if (document.data["preventivo"] != null){
                        val listOfPrev = ArrayList<String>()
                        val prevs = document.data["preventivo"] as HashMap<*, *>
                        prevs.forEach { p ->
                            val obj = p.value as HashMap<String, Boolean>
                            if(obj["deleted"] != true){
                                listOfPrev.add(p.key.toString())
                            }
                        }
                        commitments.add(Commitment(document.id, document.data["name"].toString(), document.data["number"].toString(), document.data["closed"].toString().toBoolean(), listOfPrev))
                    }else{
                        commitments.add(Commitment(document.id, document.data["name"].toString(), document.data["number"].toString(), document.data["closed"].toString().toBoolean(), ArrayList()))
                    }
                }
                Log.d(TAG, commitments.toString())
                callback(employee, commitments)
            }
    }

    fun startWork(context: Context, employee: Employee, commitment: Commitment, workId: String, machine: String, callback: ()-> Unit){
        val timestamp = Timestamp.now()

        db.collection(context.getString(R.string.collection_jobs))
            .add(mapOf(
            "start" to timestamp,
            "end" to null,
            "employee" to employee.getId(),
            "machine" to machine,
            "commitment" to commitment.getId(),
            "work" to workId
        )).addOnSuccessListener {
                Log.d("NEWWORK", "SUCCESS")
                callback()
                /*
                run {
                    addWorkToEmployee(
                        employee.getId(),
                        commitment.getId(),
                        documentReference.id,
                        callback
                    )
                }

                 */
        }.addOnFailureListener {
                Log.d("NEWWORK", "FAILURE")
            }
    }

    private fun addWorkToEmployee(context: Context, employee: String, commitment: String, workID: String, callback: () -> Unit){
        db.collection(context.getString(R.string.collection_employee))
            .document(employee)
            .update("runningWorks", FieldValue.arrayUnion(workID))
            .addOnSuccessListener {
                addWorkToCommitment(context, commitment, workID, callback)
            }
    }

    private fun addWorkToCommitment(context: Context, commitment: String, workID: String, callback: () -> Unit){
        db.collection(context.getString(R.string.collection_commesse))
            .document(commitment)
            .update("runningWorks", FieldValue.arrayUnion(workID))
            .addOnSuccessListener {
                callback()
            }
    }

    fun getWorks(
        context: Context,
        employeeId: String,
        commitments: ArrayList<Commitment>,
        callback: (ArrayList<Job>) -> Unit
    ){

        db.collection(context.getString(R.string.collection_jobs))
            .whereEqualTo("employee", employeeId)
            .get()
            .addOnSuccessListener {documents ->
                val ret = ArrayList<Job>()
                for (document in documents) {
                    if(document.data["end"] == null){

                        var commitmentName = "Error"
                        for(c in commitments){
                            if(c.getId() == document.data["commitment"].toString()){
                                commitmentName = c.getName()
                            }
                        }
                        ret.add(Job(document.id, commitmentName, document.data["commitment"].toString(), document.data["work"].toString()))
                    }
                }
                callback(ret)
            }
    }

    fun endJob(context: Context, jobs: ArrayList<Job>, callback: () -> Unit){
        jobs.forEach{ job ->
            db.collection(context.getString(R.string.collection_jobs))
                .document(job.id)
                .update("end", Timestamp.now())
        }
        callback()
    }
}