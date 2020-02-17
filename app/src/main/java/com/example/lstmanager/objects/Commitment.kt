package com.example.lstmanager.objects

import com.google.firebase.firestore.QuerySnapshot
import java.io.Serializable
import java.sql.Time
import java.sql.Timestamp

class Commitment(
    private val id: String,
    private val name: String,
    private val number: String,
    private val closed: Boolean,
    private val preventivo: ArrayList<String>
): Serializable {

    override fun toString(): String {
        return "Commitment(id='$id', name='$name', number='$number')"
    }

    fun getId(): String{
        return id
    }

    fun getName(): String{
        return name
    }

    fun getNumber(): String{
        return number
    }

    fun isClosed(): Boolean{
        return closed
    }

    fun getPreventivo():  ArrayList<String>{
        return preventivo
    }

    fun getPairForListView(): ArrayList<String>{
        return preventivo
    }
}