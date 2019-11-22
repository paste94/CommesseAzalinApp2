package com.example.lstmanager.ui

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lstmanager.R
import com.example.lstmanager.objects.Commitment
import kotlinx.android.synthetic.main.activity_choose_machine.*

class ChooseMachine : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_machine)

        setSupportActionBar(machineToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        machineToolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.common_google_signin_btn_text_dark), PorterDuff.Mode.SRC_ATOP)


        val commitment = intent?.getSerializableExtra("commitment") as Commitment

        Log.d("COMMITMENT", commitment.toString())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
