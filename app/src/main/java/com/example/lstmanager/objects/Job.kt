package com.example.lstmanager.objects

import java.io.Serializable

class Job(
    val id: String,
    val commitment: String,
    val commitmentId: String,
    val work: String
): Serializable {
    override fun toString(): String {
        return "Job(id='$id', commitment='$commitment', commitmentId='$commitmentId', work='$work')"
    }
}