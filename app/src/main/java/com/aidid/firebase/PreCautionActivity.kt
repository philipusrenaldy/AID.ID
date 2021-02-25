package com.aidid.firebase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.aidid.firebase.selectitem.adapters.PreCautionsAdapter
import com.aidid.firebase.selectitem.models.Model
import kotlinx.android.synthetic.main.activity_precaution.*

class PreCautionActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_precaution)

        recyclerView2.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        val precautionsList = ArrayList<Model>()
        precautionsList.add(Model(R.drawable.soap,"Hand Wash","Wash your hands regularly. Use soap and water, or an alcohol-based hand sanitizer."))
        precautionsList.add(Model(R.drawable.hone,"Stay Home","Don't leave the house if you don't feel well."))
        precautionsList.add(Model(R.drawable.distance,"Social Distance","Always maintain a safe distance from people who are coughing or sneezing."))
        precautionsList.add(Model(R.drawable.clean,"Clean Object & Surface","Always clean any object you touch."))
        precautionsList.add(Model(R.drawable.cover,"Avoid Touching","Don't touch your eyes, nose or mouth after u touching any object."))

        val precautionsAdapter = PreCautionsAdapter(precautionsList)
        recyclerView2.adapter = precautionsAdapter
    }
}