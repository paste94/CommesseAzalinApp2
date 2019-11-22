package com.example.lstmanager.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.KeyEvent.ACTION_UP
import android.view.View
import com.example.lstmanager.DAO.FirebaseDAO
import com.example.lstmanager.R
import com.example.lstmanager.objects.Commitment
import com.example.lstmanager.objects.Employee
import kotlinx.android.synthetic.main.activity_choose_commitment.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(mainToolbar)

        editEmployeeCode.text = null

        editEmployeeCode.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KEYCODE_ENTER && event.action == ACTION_UP) {
                progressBar.visibility = View.VISIBLE
                editEmployeeCode.isEnabled = false
                val code = editEmployeeCode.text.toString()

                val callback: (Employee?, ArrayList<Commitment>) -> Unit = { employee: Employee?, commitments: ArrayList<Commitment> ->
                    if (employee == null){
                        Toast.makeText(this, "Codice impiegato non trovato!", Toast.LENGTH_LONG).show()
                    }else{
                        val intent = Intent(this, ChooseCommitment::class.java)
                        intent.putExtra("employee", employee)
                        intent.putExtra("commitments", commitments)
                        startActivity(intent)
                    }
                    editEmployeeCode.text = null
                    progressBar.visibility = View.GONE
                    editEmployeeCode.isEnabled = true
                }

                FirebaseDAO().getEmployee(code, callback)

                return@OnKeyListener true
            }
            false
        })

    }



}
