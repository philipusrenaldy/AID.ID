package com.aidid.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aidid.firebase.selectitem.fragment.ProfileFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        auth = FirebaseAuth.getInstance()
        val fragment1 = ProfileFragment()
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment1)
    }
}