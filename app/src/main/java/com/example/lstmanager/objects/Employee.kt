package com.example.lstmanager.objects

import java.io.Serializable

class Employee (
    private val id: String,
    private val name: String,
    private val surname: String
): Serializable {

    fun getId(): String{
        return id
    }

    fun getName(): String{
        return name
    }

    fun getSurname(): String{
        return surname
    }

    override fun toString(): String {
        return "Employee(id='$id', name='$name', surname='$surname')"
    }
}