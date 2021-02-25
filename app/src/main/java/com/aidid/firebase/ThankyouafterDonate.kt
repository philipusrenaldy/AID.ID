package com.aidid.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_confirmationdonation.*
import kotlinx.android.synthetic.main.activity_thankyouafter_donate.*

class ThankyouafterDonate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thankyouafter_donate)
        btn_backhome.setOnClickListener {
            val intent = Intent(this@ThankyouafterDonate, NewHomeActivity::class.java)
            startActivity(intent)
        }
    }
}