package com.aidid.firebase

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.aidid.firebase.selectitem.adapters.NewAdapter
import com.aidid.firebase.selectitem.models.NewModel
import kotlinx.android.synthetic.main.activity_hospital2.*

class HospitalActivity : AppCompatActivity() {
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital2)

        var listview = findViewById<ListView>(R.id.hospitalview)
        var list = mutableListOf<NewModel>()

        list.add(NewModel("RS Universitas Indonesia", "Pondok Cina, Kecamatan Beji, Kota Depok, Jawa Barat 16424", "+622150829292", R.drawable.rsui))
        list.add(NewModel("RSU Bunda Margonda", "Jl. Margonda Raya No.28, Pondok Cina, Kecamatan Beji, Kota Depok, Jawa Barat 16424", "+621500799",R.drawable.rsubundamargonda))
        list.add(NewModel("RS Grha Permata Ibu", "Jl. K.H.M. Usman No.168, Kukusan, Kecamatan Beji, Kota Depok, Jawa Barat 16425", "+62217778899",R.drawable.rsgrhapermataibu))
        list.add(NewModel("RS Citra Arafiq", "Jl. Perindustrian No.53, Bakti Jaya, Kec. Sukmajaya, Kota Depok, Jawa Barat 16418", "+622122821911",R.drawable.rscitraarafiq))
        list.add(NewModel("RS Universitas Hasanuddin", "Jl. Perintis Kemerdekaan No.KM 10, Tamalanrea Indah, Kec. Tamalanrea, Kota Makassar, Sulawesi Selatan 90245", "+62411591331",R.drawable.rshasanuddin))
        list.add(NewModel("RSUP Fatmawati", "Jl. RS. Fatmawati Raya No.4, RT.4/RW.9, Cilandak Bar., Kec. Cilandak, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12430", "+62217501524",R.drawable.rsupfatmawati))

        listview.adapter = NewAdapter(this, R.layout.row, list)

        listview.setOnItemClickListener { parent:AdapterView<*>, view:View, position:Int, id:Long ->
            if (position == 0){
                var intent = Intent(this@HospitalActivity,MainSelectItem::class.java)
                startActivity(intent)
            }
            if (position == 1){
                var intent = Intent(this@HospitalActivity,MainSelectItem::class.java)
                startActivity(intent)
            }
            if (position == 2){
                var intent = Intent(this@HospitalActivity,MainSelectItem::class.java)
                startActivity(intent)
            }
            if (position == 3){
                var intent = Intent(this@HospitalActivity,MainSelectItem::class.java)
                startActivity(intent)
            }
            if (position == 4){
                var intent = Intent(this@HospitalActivity,MainSelectItem::class.java)
                startActivity(intent)
            }
        }
    }
}