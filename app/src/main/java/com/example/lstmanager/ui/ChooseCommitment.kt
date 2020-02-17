package com.example.lstmanager.ui

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.app.TaskStackBuilder
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lstmanager.R
import com.example.lstmanager.adapter.CommitmentAdapter
import com.example.lstmanager.adapter.CommitmentExpandableListAdapter
import com.example.lstmanager.adapter.CommitmentListAdapter
import com.example.lstmanager.objects.Commitment
import com.example.lstmanager.objects.Employee
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_choose_commitment.*

class ChooseCommitment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_commitment)

        setSupportActionBar(commitmentToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        commitmentToolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.common_google_signin_btn_text_dark), PorterDuff.Mode.SRC_ATOP)

        // Roba arrivata dalla activity precedente
        val jsonString = intent?.getStringExtra("employee")
        val employee = Gson().fromJson<Employee>(jsonString!!)
        val commitments = intent?.getSerializableExtra("commitments") as ArrayList<Commitment>

        // Mostra la scritta
        val str = "Benvenuto " + employee.getName() + " " + employee.getSurname()
        employeeCode.text = str

        // Roba della ExpandableListView
        val expandableListView = findViewById<ExpandableListView>(R.id.commitmentsExpListView)

        val listOfCommitmentsId = ArrayList<String>()
        commitments.forEach{ e ->
            if(!e.isClosed()){
                listOfCommitmentsId.add(e.getNumber())
            }
        }

        val expListData = HashMap<String, ArrayList<String>>()
        commitments.forEach{ e ->
            if(!e.isClosed()){
                val p = e.getPairForListView()
                expListData[e.getNumber()] = p
            }
        }

        val expandableListAdapter = CommitmentExpandableListAdapter(this, listOfCommitmentsId, expListData)
        expandableListView.setAdapter(expandableListAdapter)

        expandableListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            val jsonEmployee = Gson().toJson(employee)
            val intent = Intent(this, ChooseMachine::class.java)
            intent.putExtra("commitment", commitments[groupPosition])
            intent.putExtra("employee", jsonEmployee)
            intent.putExtra("workId", expListData[listOfCommitmentsId[groupPosition]]!![childPosition])
            startActivity(intent)
            false
        }

        /*
        val lv = findViewById<ListView>(R.id.commitmentListView)
        val adapter = CommitmentListAdapter(commitments, applicationContext)
        lv.adapter = adapter

        lv.onItemClickListener = AdapterView.OnItemClickListener {
                adapterView, view, i, l ->

            val jsonEmployee = Gson().toJson(employee)
            val intent = Intent(this, ChooseMachine::class.java)
            intent.putExtra("commitment", commitments[i])
            intent.putExtra("employee", jsonEmployee)
            startActivity(intent)

        }

         */


    }

    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
