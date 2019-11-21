package com.example.lstmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.KeyEvent
import android.view.KeyEvent.ACTION_UP
import android.view.View
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.activity_choose_commitment.*
import java.io.Serializable


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editEmployeeCode.text = null

        editEmployeeCode.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KEYCODE_ENTER && event.action == ACTION_UP) {
                progressBar.visibility = View.VISIBLE
                editEmployeeCode.isEnabled = false
                val code = editEmployeeCode.text.toString()

                val callback: (HashMap<String, String>) -> Unit = { employee: HashMap<String, String> ->
                    val intent = Intent(this, ChooseCommitment::class.java)
                    intent.putExtra("employee", employee)
                    startActivity(intent)

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
