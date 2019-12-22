package com.example.lstmanager.ui

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.lstmanager.DAO.FirebaseDAO
import com.example.lstmanager.R
import com.example.lstmanager.objects.Commitment
import com.example.lstmanager.objects.Employee
import com.example.lstmanager.objects.Job
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_choose_machine.*
import kotlinx.android.synthetic.main.activity_choose_new_or_close.*

class ChooseNewOrClose : AppCompatActivity() {

    lateinit var commitments: ArrayList<Commitment>
    lateinit var employee: Employee

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_new_or_close)

        setSupportActionBar(newOrCloseToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        newOrCloseToolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.common_google_signin_btn_text_dark), PorterDuff.Mode.SRC_ATOP)

        commitments = intent?.getSerializableExtra("commitments") as ArrayList<Commitment>
        val jsonEmployee = intent?.getStringExtra("employee")
        employee = Gson().fromJson<Employee>(jsonEmployee!!)

        Log.d("EMPLOYEE", employee.toString())

    }

    fun onClick(v: View){
        when(v.id){
            R.id.newWork -> {
                val jsonString = Gson().toJson(employee)
                val intent = Intent(this, ChooseCommitment::class.java)
                intent.putExtra("employee", jsonString)
                intent.putExtra("commitments", commitments)
                startActivity(intent)
            }
            R.id.end -> {
                val callback: (ArrayList<Job>) -> Unit = {works: ArrayList<Job> ->
                    endProgressBar.visibility = View.GONE
                    val jsonString = Gson().toJson(employee)
                    val intent = Intent(this, EndWork::class.java)
                    intent.putExtra("employee", jsonString)
                    intent.putExtra("works", works)
                    startActivity(intent)
                }
                endProgressBar.visibility = View.VISIBLE
                FirebaseDAO().getWorks(applicationContext, employee.getId(), commitments, callback)
            }
        }
    }

    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
