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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_choose_machine.*

class ChooseMachine : AppCompatActivity() {

    lateinit var commitment: Commitment
    private lateinit var employee: Employee
    private lateinit var workId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_machine)

        setSupportActionBar(machineToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        machineToolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.common_google_signin_btn_text_dark), PorterDuff.Mode.SRC_ATOP)

        commitment = intent?.getSerializableExtra("commitment") as Commitment
        val jsonEmployee = intent?.getStringExtra("employee")
        workId = intent?.getStringExtra("workId")!!

        employee = Gson().fromJson(jsonEmployee!!)

        Log.d("COMMITMENT", commitment.toString())
    }

    private inline fun <reified T> Gson.fromJson(json: String): T = this.fromJson<T>(json, object: TypeToken<T>() {}.type)!!

    fun onClick(v: View){
        btnBanco.isEnabled = false
        btnCN1.isEnabled = false
        btnCN2.isEnabled = false
        btnCN3.isEnabled = false
        btnCN4.isEnabled = false
        btnCN5.isEnabled = false
        btnFresa.isEnabled = false
        btnRettifica.isEnabled = false
        btnSquadr.isEnabled = false
        btnStozz.isEnabled = false
        btnTornio.isEnabled = false
        machineProgressBar.visibility = View.VISIBLE
        val callback: () -> Unit = {
            backToMain()
        }
        when(v.id){
            R.id.btnBanco -> {
                FirebaseDAO().startWork(applicationContext, employee, commitment, workId, "Banco" , callback)
            }
            R.id.btnCN1 -> {
                FirebaseDAO().startWork(applicationContext, employee, commitment, workId, "CN1" , callback)
            }
            R.id.btnCN2 -> {
                FirebaseDAO().startWork(applicationContext, employee, commitment, workId, "CN2" , callback)
            }
            R.id.btnCN3 -> {
                FirebaseDAO().startWork(applicationContext, employee, commitment, workId, "CN3" , callback)
            }
            R.id.btnCN4 -> {
                FirebaseDAO().startWork(applicationContext, employee, commitment, workId, "CN4" , callback)
            }
            R.id.btnCN5 -> {
                FirebaseDAO().startWork(applicationContext, employee, commitment, workId, "CN5" , callback)
            }
            R.id.btnFresa -> {
                FirebaseDAO().startWork(applicationContext, employee, commitment, workId, "Fresa" , callback)
            }
            R.id.btnRettifica -> {
                FirebaseDAO().startWork(applicationContext, employee, commitment, workId, "Rettifica" , callback)
            }
            R.id.btnSquadr -> {
                FirebaseDAO().startWork(applicationContext, employee, commitment, workId, "Squadr" , callback)
            }
            R.id.btnStozz -> {
                FirebaseDAO().startWork(applicationContext, employee, commitment, workId, "Stozz" , callback)
            }
            R.id.btnTornio -> {
                FirebaseDAO().startWork(applicationContext, employee, commitment, workId, "Tornio" , callback)
            }
        }
    }


    private fun backToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
