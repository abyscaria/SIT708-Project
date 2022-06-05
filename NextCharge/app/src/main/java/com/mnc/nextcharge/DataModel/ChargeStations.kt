package com.mnc.nextcharge.DataModel

import com.google.firebase.database.DataSnapshot

class ChargeStations(snapshot : DataSnapshot) {
    lateinit var id : String
    lateinit var address : String
    lateinit var altDest1 : String
    lateinit var altDest2 : String
    lateinit var altDest3 : String
    lateinit var altDest4 : String
    lateinit var chargerQuantity : String
    lateinit var chargerType : String
    lateinit var chargerCapacity : String
    lateinit var city : String
    lateinit var csID : String
    lateinit var linkedRoad : String
    lateinit var location : String
    lateinit var name : String
    lateinit var postcode : String
    lateinit var premise : String
    lateinit var state : String

    init {
        try {
            val data : HashMap<String, Any> =
                snapshot.value as HashMap<String, Any> /* = java.util.HashMap<kotlin.String, kotlin.Any> */
            id = snapshot.key ?: ""
            //address = data["address"] as String
            //altDest1 = data["altDest1"] as String
            // altDest2 = data["altDest2"] as String
            //altDest3 = data["altDest3"] as String
            //altDest4 = data["altDest4"] as String
            chargerQuantity = data["chargerQuantity"] as String
            //chargerType = data["chargerType"] as String
            chargerCapacity = data["chargerCapacity"] as String
            city = data["city"] as String
            //csID = data["csID"] as String
            // linkedRoad = data["linkedRoad"] as String
            // location = data["location"] as String
            name = data["name"] as String
            //postcode = data["postcode"] as String
            //premise = data["premise"] as String
            // state = data["state"] as String
        } catch (e : Exception) {
            e.printStackTrace()
        }
    }

}