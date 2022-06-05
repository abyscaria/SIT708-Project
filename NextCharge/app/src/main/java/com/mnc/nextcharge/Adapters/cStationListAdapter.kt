package com.mnc.nextcharge.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.mnc.nextcharge.DataModel.ChargeStations
import com.mnc.nextcharge.R as R1


/* cStationListAdapter : This adapter deliver data to to Plan a Trip UI and Charge StationList UI
// This Adapter links the layouts and firebase database to construct the card layout to present the data
 */

class cStationListAdapter(context : Context, resource: Int, list : ArrayList<ChargeStations>) :ArrayAdapter<ChargeStations>(context,resource,list) {
    private var csResource : Int = 0
    private var cList : ArrayList<ChargeStations>
    private var csLayoutInflater : LayoutInflater
    private var csContext : Context = context
    var tag = "ChargeStationAdapter"
    init {
        this.csResource = resource
        this.cList = list
        this.csLayoutInflater =
            csContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position : Int, convertView : View?, parent : ViewGroup) : View {
        val returnView : View
        if (convertView == null) {
            returnView = try {csLayoutInflater.inflate(csResource, null)
            } catch (e : Exception) {
                e.printStackTrace()
                View(context)
            }

            setUI(returnView, position)
            return returnView
        }
        setUI(convertView, position)
        return convertView
    }

   private fun setUI(view: View, position : Int) {
       val cstation : ChargeStations? = if (count > position) getItem(position) else null
       val csName : TextView? = view.findViewById(R1.id.csname)
       csName?.text = cstation?.name
       //Log.i(tag,"Change Station Name"+cstation?.name)
       val csCity : TextView? = view.findViewById(R1.id.cscity)
       csCity?.text = cstation?.city
       val csQantity : TextView? = view.findViewById(R1.id.csquamtity)
       csQantity?.text = cstation?.chargerQuantity
       val csCapacity : TextView? = view.findViewById(R1.id.cscapacity)
       csCapacity?.text = cstation?.chargerCapacity


   }
}