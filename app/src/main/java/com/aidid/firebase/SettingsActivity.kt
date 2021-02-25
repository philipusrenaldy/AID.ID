package com.aidid.firebase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        etUserSupport.setOnClickListener {
            var intent = Intent(this@SettingsActivity, UsersupportActivity::class.java)
            startActivity(intent)
        }

        etProfButSet.setOnClickListener {
            var intent = Intent(this@SettingsActivity, HomeActivity::class.java)
            startActivity(intent)
        }

        etTNC.setOnClickListener {
            var intent = Intent(this@SettingsActivity, TncActivity::class.java)
            startActivity(intent)
        }

        etabout.setOnClickListener {
            var intent = Intent(this@SettingsActivity, AboutusActivity::class.java)
            startActivity(intent)
        }
    }
}