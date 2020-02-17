package com.example.lstmanager.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lstmanager.BuildConfig
import com.example.lstmanager.R
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        version.text = "Versione: " + BuildConfig.VERSION_NAME

        btn_update.setOnClickListener{
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://mega.nz/#F!j501iSJY!vj9UvAgn-iUfCiiLlA2NDA"))
            startActivity(i)
        }
    }
}
