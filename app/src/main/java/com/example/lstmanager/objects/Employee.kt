package com.example.lstmanager.objects

import java.io.Serializable

class Employee(
    private val id: String,
    private val name: String,
    private val surname: String,
    private val currentWorks: ArrayList<*>
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

    fun getCurrentWorks(): ArrayList<*> {
        return currentWorks
    }

    override fun toString(): String {
        return "Employee(id='$id', name='$name', surname='$surname', currentWorks=$currentWorks)"
    }


}