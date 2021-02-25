package com.aidid.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.aidid.firebase.selectitem.adapters.NewAdapter
import com.aidid.firebase.selectitem.models.NewModel
import kotlinx.android.synthetic.main.activity_orphan.*

class OrphanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orphan)
        var listview = findViewById<ListView>(R.id.orphanview)
        var list = mutableListOf<NewModel>()
        list.add(NewModel("Panti Asuhan Darul Ilmi", "Jl. KH. Ahmad Dahlan No.24, Beji Tim., Kecamatan Beji, Kota Depok, Jawa Barat 16425", "++6281380389130", R.drawable.darulilmi))
        list.add(NewModel("Panti Asuhan Aisyiah Cipedak", "Jl. Kavling-Komplek DKI No.11, RT.6/RW.3, Cipedak, Kec. Jagakarsa, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12630", "+622178886045",R.drawable.aisiyah))
        list.add(NewModel("Panti Asuhan An Nur", "Jl. Bumi Pancoran Mas No. 27, RT. 03 / RW. 12, Mampang, Kec. Pancoran Mas, Kota Depok, Jawa Barat 16433", "+62217751592",R.drawable.annur))
        list.add(NewModel("Panti Asuhan Pondok Si Boncel", "Jl. Boncel No.5, RT.7/RW.5, Srengseng Sawah, Kec. Jagakarsa, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12630", "+62217271014",R.drawable.pantiboncel))
        listview.adapter = NewAdapter(this, R.layout.row, list)
        listview.setOnItemClickListener { parent:AdapterView<*>, view:View, position:Int, id:Long ->
            if (position == 0){
                var intent = Intent(this@OrphanActivity,MainSelectItem::class.java)
                startActivity(intent)
            }
            if (position == 1){
                var intent = Intent(this@OrphanActivity,MainSelectItem::class.java)
                startActivity(intent)
            }
            if (position == 2){
                var intent = Intent(this@OrphanActivity,MainSelectItem::class.java)
                startActivity(intent)
            }
            if (position == 3){
                var intent = Intent(this@OrphanActivity,MainSelectItem::class.java)
                startActivity(intent)
            }
        }
    }
}