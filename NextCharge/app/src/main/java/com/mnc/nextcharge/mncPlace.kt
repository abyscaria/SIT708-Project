package com.mnc.nextcharge

import com.google.android.gms.maps.model.LatLng

data class mncPlace(
    val name: String?=null,
    val latLng: LatLng?=null,
    val address: LatLng?=null,
    val rating: Float?=null)