package com.aidid.firebase.selectitem.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.aidid.firebase.R
import com.aidid.firebase.selectitem.models.NewModel

class NewAdapter(var mCtx: Context, var resource:Int, var items: List<NewModel>):ArrayAdapter<NewModel> (mCtx, resource, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater = LayoutInflater.from(mCtx)
        val view:View = layoutInflater.inflate(resource, null)

        val imageView:ImageView = view.findViewById(R.id.image123)
        val titleTextView:TextView = view.findViewById(R.id.text123)
        val descriptionTextView:TextView = view.findViewById(R.id.text124)
        val extendTextView:TextView = view.findViewById(R.id.text125)

        var mItem:NewModel = items[position]
        imageView.setImageDrawable(mCtx.resources.getDrawable(mItem.img))
        titleTextView.text = mItem.title
        descriptionTextView.text = mItem.description
        extendTextView.text = mItem.extend

        return view
    }
}