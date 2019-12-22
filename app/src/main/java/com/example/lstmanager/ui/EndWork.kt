package com.example.lstmanager.ui

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lstmanager.DAO.FirebaseDAO
import com.example.lstmanager.R
import com.example.lstmanager.adapter.CommitmentEndListAdapter
import com.example.lstmanager.objects.Employee
import com.example.lstmanager.objects.Job
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_end_work.*


class EndWork : AppCompatActivity() {

    lateinit var adapter: CommitmentEndListAdapter
    lateinit var lv: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_work)

        setSupportActionBar(commitmentEndToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        commitmentEndToolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.common_google_signin_btn_text_dark), PorterDuff.Mode.SRC_ATOP)

        val jsonString = intent?.getStringExtra("employee")
        val employee = Gson().fromJson<Employee>(jsonString!!)
        val works = intent?.getSerializableExtra("works") as ArrayList<Job>

    /*
        for( element in employee.getCurrentWorks()){
            val e = element as com.google.firebase.Timestamp
            for (c in allCommitments){
                if(c.getId() == e){
                    commitments.add(c)
                }
            }
        }

     */

        Log.d("WORKS", works.toString())

        lv = findViewById<ListView>(R.id.commitmentToEndListView)

        adapter = CommitmentEndListAdapter(works, applicationContext)
        lv.adapter = adapter
    }

    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun onClick(v: View) {
        when(v.id) {
            R.id.selectAll -> {
                selectAll(true)
            }
            R.id.deselectAll -> {
                selectAll(false)
            }
            R.id.finish -> {
                endJobs()
            }
        }
    }

    private fun selectAll(sel: Boolean){
        if(sel){
            for (i in 0 until lv.childCount) {
                adapter.checkItemTrue(i)
            }
        }else{
            for (i in 0 until lv.childCount) {
                adapter.checkItemFalse(i)
            }
        }
    }

    private fun endJobs(){
        val jobsToEnd = adapter.getSelectedJobs()
        val callback: () -> Unit = {
            startActivity(Intent(this, MainActivity::class.java))
        }
        FirebaseDAO().endJob(applicationContext, jobsToEnd, callback)
    }
}
