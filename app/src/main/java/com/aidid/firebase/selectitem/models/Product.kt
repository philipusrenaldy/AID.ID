package com.aidid.firebase.selectitem.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    var id: String? = "",
    var name: String? = "",
    var points: Int? = null,
    var image: String? = "",
    var selectedQuantity: Int? = null
) : Parcelable