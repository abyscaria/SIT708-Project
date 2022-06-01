package com.mnc.nextcharge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mnc.nextcharge.databinding.FragmentPlantripBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PlanTripFragment : Fragment() {
    private var _binding : FragmentPlantripBinding? = null
    private val binding get() = _binding!!
     //val context : Context? = null
    // This property is only valid between onCreateView and
    // onDestroyView.


    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {

        _binding = FragmentPlantripBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner: Spinner =  binding.evSelector              //findViewById(R.id.spinner)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            activity?.applicationContext!!,
                R.array.EVehicles,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }


        binding.searchButton.setOnClickListener {
            // Set the LayoutManager that this RecyclerView will use.
            binding.recyclerViewItems.layoutManager = LinearLayoutManager(activity?.applicationContext!!)

            // Adapter class is initialized and list is passed in the param.
           // val itemAdapter = ItemAdapter(activity?.applicationContext!!, getItemsList())
            //val itemAdapter = ItemAdapter(activity?.applicationContext!!, getItemsList())
            // adapter instance is set to the recyclerview to inflate the items.
            //binding.recyclerViewItems.adapter = itemAdapter
        }

        /**
         * Function is used to get the Items List which is added in the list.
         */


    }
   private fun getItemsList(): ArrayList<String> {
    val list =   ArrayList<String>()
     //val dataitem =  ArrayList<String>(R.array.EVehicles)

    /*for (i in 1..15) {
        list.add("Item $i")
    }*/
    for (i in 1..15) {
           list.add("Tesla Model $i")
       }
    return list
}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}