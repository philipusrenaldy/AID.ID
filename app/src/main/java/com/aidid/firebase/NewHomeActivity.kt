package com.aidid.firebase

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.aidid.firebase.selectitem.adapters.PreCautionsAdapter
import com.aidid.firebase.selectitem.adapters.SymptomsAdapter
import com.aidid.firebase.selectitem.models.Model
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_new_home.*
import kotlinx.android.synthetic.main.activity_new_home.toolbar
import org.json.JSONObject

class NewHomeActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_home)
        setSupportActionBar(toolbar)
        auth = FirebaseAuth.getInstance()

        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayout.HORIZONTAL,false)
        val symptomsList = ArrayList<Model>()
        symptomsList.add(Model(R.drawable.cough,"Dry Cough","Dry Cough is a reflex action that clears your airway of irritants and mucus. There are two types of cough: productive and nonproductive. A productive cough produces phlegm or mucus, clearing it from the lungs. A nonproductive cough, also known as a dry cough, doesn’t produce phlegm or mucus."))
        symptomsList.add(Model(R.drawable.fever,"Fever","Fever is a temporary increase in your body temperature, often due to an illness. Having a fever is a sign that something out of the ordinary is going on in your body."))
        symptomsList.add(Model(R.drawable.pain,"Head Ache","Headache is the symptom of pain in the face, head, or neck. It can occur as a migraine, tension-type headache, or cluster headache."))

        val symptomsAdapter = SymptomsAdapter(symptomsList)
        recyclerView.adapter = symptomsAdapter

        recyclerViewPrecautions.layoutManager = LinearLayoutManager(this,LinearLayout.HORIZONTAL,false)
        val precautionsList = ArrayList<Model>()
        precautionsList.add(Model(R.drawable.soap,"Hand Wash","Wash your hands regularly. Use soap and water, or an alcohol-based hand sanitizer."))
        precautionsList.add(Model(R.drawable.hone,"Stay Home","Don't leave the house if you don't feel well."))
        precautionsList.add(Model(R.drawable.distance,"Social Distance","Always maintain a safe distance from people who are coughing or sneezing."))

        val precautionsAdapter = PreCautionsAdapter(precautionsList)
        recyclerViewPrecautions.adapter = precautionsAdapter

        txtViewSymptoms.setOnClickListener {
            var intent = Intent(this@NewHomeActivity,SymptomsActivity::class.java)
            startActivity(intent)
        }

        txtViewPrecautions.setOnClickListener {
            var intent = Intent(this@NewHomeActivity,PreCautionActivity::class.java)
            startActivity(intent)
        }

        hospitalbutton.setOnClickListener{
            var intent = Intent(this@NewHomeActivity, HospitalActivity::class.java)
                startActivity(intent)
        }

        orphanbutton.setOnClickListener{
            var intent = Intent(this@NewHomeActivity, OrphanActivity::class.java)
            startActivity(intent)
        }

        antivirusbutton.setOnClickListener {
            Toast.makeText(this@NewHomeActivity,"You clicked on item Antivirus, nothing inside here please please select another",Toast.LENGTH_SHORT).show()
        }

        schoolbutton.setOnClickListener {
            Toast.makeText(this@NewHomeActivity, "You clicked on item School, nothing inside here please please select another",Toast.LENGTH_SHORT).show()
        }
        getGlobalData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {

        when(item.itemId)
        {
            R.id.logout->{
                auth.signOut()
                Intent(this@NewHomeActivity, LoginActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_MULTIPLE_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
                return true
            }
            R.id.settings-> {
                Intent(this@NewHomeActivity, SettingsActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_MULTIPLE_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
                return true
            }
            R.id.Profileopt-> {
                Intent(this@NewHomeActivity, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_MULTIPLE_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
                return true
            }
            R.id.Reward-> {
                Intent(this@NewHomeActivity, RewardsActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_MULTIPLE_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
                return true
            }
            else -> return true
        }

    }

    fun getGlobalData(){
        val url:String ="https://corona.lmao.ninja/v3/covid-19/countries/indonesia"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
                {

                    var jsonObject = JSONObject(it.toString())

                    //now set values in textview
                    txtInfected.text = jsonObject.getString("cases")
                    txtRecoverd.text = jsonObject.getString("recovered")
                    txtDeceased.text = jsonObject.getString("deaths")

                },
                {
                    Toast.makeText(this@NewHomeActivity,it.toString(), Toast.LENGTH_LONG).show()
                    txtInfected.text = "-"
                    txtRecoverd.text = "-"
                    txtDeceased.text = "-"

                }
        )

        val requestQueue = Volley.newRequestQueue(this@NewHomeActivity)
        requestQueue.add(stringRequest)
    }
}