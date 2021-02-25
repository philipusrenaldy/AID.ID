package com.aidid.firebase

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.aidid.firebase.selectitem.adapters.ProductAdapter
import com.aidid.firebase.selectitem.adapters.SelectedProductAdapter
import com.aidid.firebase.selectitem.viewmodels.ProductViewModel
import kotlinx.android.synthetic.main.activity_mainselectitem.*
import kotlinx.android.synthetic.main.bottomsheet_cart.*

class MainSelectItem : AppCompatActivity() {
    private lateinit var productViewModel: ProductViewModel
    private lateinit var bottomSheet : BottomSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainselectitem)
        setupUI()
        btn_checkout.setOnClickListener {
            val intent = Intent(this@MainSelectItem,Confirmationdonation::class.java)
            startActivity(intent)
        }
    }

    private fun setupUI(){
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        rv_product.apply {
            layoutManager = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                GridLayoutManager(this@MainSelectItem, 2)
            }else{
                GridLayoutManager(this@MainSelectItem, 4)
            }
            adapter = ProductAdapter(mutableListOf(), this@MainSelectItem, productViewModel)
        }
        rv_selected_product.apply {
            layoutManager = LinearLayoutManager(this@MainSelectItem)
            adapter = SelectedProductAdapter(mutableListOf(), this@MainSelectItem, productViewModel)
        }
        bottomSheet = BottomSheetBehavior.from(bottomsheet_detail_order)
        bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
        btn_detail.setOnClickListener {
            if (bottomSheet.state == BottomSheetBehavior.STATE_COLLAPSED || bottomSheet.state == BottomSheetBehavior.STATE_HIDDEN){
                bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
            }else{
                bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
            }
        }
        btn_checkout.setOnClickListener {
            Toast.makeText(this@MainSelectItem, "Checkout", Toast.LENGTH_LONG).show()
        }
        productViewModel.fetchDummyProduct()
        productViewModel.listenToProducts().observe(this, Observer {
            rv_product.adapter?.let { a ->
                if(a is ProductAdapter){
                    a.updateList(it)
                }
            }
        })
        productViewModel.listenToSelectedProduct().observe(this, Observer {
            rv_selected_product.adapter?.let { a->
                if(a is SelectedProductAdapter){
                    a.updateList(it)
                }
            }
            val totalpoints = if(it.isEmpty()){ 0 }else{
                it.sumBy { p ->
                    p.points!! * p.selectedQuantity!!
                }
            }
            total_points.text = totalpoints.toString()
        })
    }
}