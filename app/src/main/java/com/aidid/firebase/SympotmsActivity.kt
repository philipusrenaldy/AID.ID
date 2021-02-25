package com.aidid.firebase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.aidid.firebase.selectitem.adapters.SymptomsAdapter
import com.aidid.firebase.selectitem.models.Model
import kotlinx.android.synthetic.main.activity_sympotms.*

class SymptomsActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sympotms)

        recyclerView1.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL,false)
        val symptomsList = ArrayList<Model>()
        symptomsList.add(Model(R.drawable.cough,"Dry Cough","Dry Cough is a reflex action that clears your airway of irritants and mucus. There are two types of cough: productive and nonproductive. A productive cough produces phlegm or mucus, clearing it from the lungs. A nonproductive cough, also known as a dry cough, doesn’t produce phlegm or mucus."))
        symptomsList.add(Model(R.drawable.fever,"Fever","Fever is a temporary increase in your body temperature, often due to an illness. Having a fever is a sign that something out of the ordinary is going on in your body."))
        symptomsList.add(Model(R.drawable.pain,"Head Ache","Headache is the symptom of pain in the face, head, or neck. It can occur as a migraine, tension-type headache, or cluster headache."))
        symptomsList.add(Model(R.drawable.sore_throat,"Sore Throat","Sore Throat is pain, scratchiness or irritation of the throat that often worsens when you swallow."))

        val symptomsAdapter = SymptomsAdapter(symptomsList)

        recyclerView1.adapter = symptomsAdapter
    }
}