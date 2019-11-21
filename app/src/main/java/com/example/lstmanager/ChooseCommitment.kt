package com.example.lstmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_choose_commitment.*
import kotlinx.android.synthetic.main.activity_main.*

class ChooseCommitment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_commitment)

        val employee =  intent?.getSerializableExtra("employee") as HashMap<*, *>
        val str = employee["name"].toString() + employee["surname"].toString()

        employeeCode.text = str

    }
}
