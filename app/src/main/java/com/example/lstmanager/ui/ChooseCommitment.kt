package com.example.lstmanager.ui

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.TaskStackBuilder
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lstmanager.R
import com.example.lstmanager.adapter.CommitmentAdapter
import com.example.lstmanager.adapter.CommitmentListAdapter
import com.example.lstmanager.objects.Commitment
import com.example.lstmanager.objects.Employee
import kotlinx.android.synthetic.main.activity_choose_commitment.*

class ChooseCommitment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_commitment)

        setSupportActionBar(commitmentToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        commitmentToolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.common_google_signin_btn_text_dark), PorterDuff.Mode.SRC_ATOP)

        val employee = intent?.getSerializableExtra("employee") as Employee
        val commitments = intent?.getSerializableExtra("commitments") as ArrayList<Commitment>
        val str = "Benvenuto " + employee.getName() + " " + employee.getSurname()


        employeeCode.text = str

        val lv = findViewById<ListView>(R.id.commitmentListView)
        val adapter = CommitmentListAdapter(commitments, applicationContext)
        lv.adapter = adapter

        lv.onItemClickListener = AdapterView.OnItemClickListener {
                adapterView, view, i, l ->

            val intent = Intent(this, ChooseMachine::class.java)
            intent.putExtra("commitment", commitments[i])
            startActivity(intent)

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
