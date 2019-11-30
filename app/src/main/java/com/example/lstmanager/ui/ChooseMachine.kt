package com.example.lstmanager.ui

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.lstmanager.DAO.FirebaseDAO
import com.example.lstmanager.R
import com.example.lstmanager.objects.Commitment
import com.example.lstmanager.objects.Employee
import kotlinx.android.synthetic.main.activity_choose_machine.*
import kotlinx.android.synthetic.main.activity_main.*

class ChooseMachine : AppCompatActivity() {

    lateinit var commitment: Commitment
    lateinit var employee: Employee

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_machine)

        setSupportActionBar(machineToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        machineToolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.common_google_signin_btn_text_dark), PorterDuff.Mode.SRC_ATOP)

        commitment = intent?.getSerializableExtra("commitment") as Commitment
        employee = intent?.getSerializableExtra("employee") as Employee

        Log.d("COMMITMENT", commitment.toString())
    }

    fun onClick(v: View){
        btnBanco.isEnabled = false
        btnCN.isEnabled = false
        btnFresa.isEnabled = false
        btnRettifica.isEnabled = false
        btnSquadr.isEnabled = false
        btnStozz.isEnabled = false
        btnTornio.isEnabled = false
        when(v.id){
            R.id.btnBanco -> {
                val callback: () -> Unit = {
                    Log.d("PROVA", "ARRIVATO")
                    backToMain()
                }
                FirebaseDAO().startWork(employee, commitment, "Banco" , callback)
            }
            R.id.btnCN -> {

            }
            R.id.btnFresa -> {

            }
            R.id.btnRettifica -> {

            }
            R.id.btnSquadr -> {

            }
            R.id.btnStozz -> {

            }
            R.id.btnTornio -> {

            }
        }
    }


    fun backToMain(){
        /*
        btnBanco.isEnabled = true
        btnCN.isEnabled = true
        btnFresa.isEnabled = true
        btnRettifica.isEnabled = true
        btnSquadr.isEnabled = true
        btnStozz.isEnabled = true
        btnTornio.isEnabled = true
         */
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
