package com.aidid.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_confirmationdonation.*

class Confirmationdonation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmationdonation)

        btn_no.setOnClickListener {
            val intent = Intent(this@Confirmationdonation, MainSelectItem::class.java)
            startActivity(intent)
        }
        btn_yes.setOnClickListener {
            val intent = Intent(this@Confirmationdonation,ThankyouafterDonate::class.java)
            startActivity(intent)
        }
    }
}