package com.example.lstmanager.objects

import com.google.firebase.firestore.QuerySnapshot
import java.io.Serializable

class Commitment(
    private val id: String,
    private val name: String,
    private val number: String
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
}