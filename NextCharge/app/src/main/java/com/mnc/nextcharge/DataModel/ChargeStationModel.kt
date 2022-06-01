package com.mnc.nextcharge.DataModel

import android.util.Log
import com.google.firebase.database.*
import java.util.*

object ChargeStationModel : Observable(){
 private var cValueDataListener : ValueEventListener? = null
 private var cStationList : ArrayList<ChargeStations>? = ArrayList()
 var tag = "ChargeStationModel"

 private fun getDataBaseRef () : DatabaseReference? {
  return FirebaseDatabase.getInstance().reference.child("chargingStations")
 }

 init {

  if (cValueDataListener != null) {
   getDataBaseRef()?.removeEventListener(cValueDataListener!!) }
   cValueDataListener = null
  Log.i(tag," inside ChargeStationsModel init line : 22")
   cValueDataListener = object: ValueEventListener {
    override fun onDataChange(dataSnapshot : DataSnapshot) {
     try {
      Log.i(tag, "Data updated ChargeStationModel : Line 26")
      val data : ArrayList<ChargeStations> = ArrayList()
      if (dataSnapshot != null) {
       for (snapshot : DataSnapshot in dataSnapshot.children) {
        try {
         data.add(ChargeStations(snapshot))
        } catch (e : Exception) {
         e.printStackTrace()
        }

       }
       cStationList = data
       Log.i(tag, "Cstatation Data updated " + cStationList!!.size + " in the cache")
       setChanged()
       notifyObservers()
      }
     } catch (e2 : Exception) { e2.printStackTrace()}

    }

    override fun onCancelled(proc0 : DatabaseError) {
     if(proc0 != null ) {
      Log.i(tag,"cstatationDataModel - data change chancelled "+proc0.message)
    }


  }

 }
getDataBaseRef()?.addValueEventListener(cValueDataListener as ValueEventListener)
}
  fun getData () : ArrayList<ChargeStations> { return cStationList!!}
}