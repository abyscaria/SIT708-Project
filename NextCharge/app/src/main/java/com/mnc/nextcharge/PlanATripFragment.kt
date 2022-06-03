package com.mnc.nextcharge

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.mnc.nextcharge.Adapters.cStationListAdapter
import com.mnc.nextcharge.DataModel.ChargeStationModel
import com.mnc.nextcharge.DataModel.ChargeStations
import com.mnc.nextcharge.databinding.FragmentPlanatripBinding
import java.util.*

class PlanATripFragment : Fragment(), Observer {
    // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding : FragmentPlanatripBinding? = null
    private val binding get() = _binding!!
    private var csListAdapter : cStationListAdapter? = null
    private var thiscontext : Context? = null
    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        thiscontext = container?.getContext();
        _binding = FragmentPlanatripBinding.inflate(inflater, container, false)
        val root : View = binding.root
        val spinner : Spinner = binding.myEVEditText              //findViewById(R.id.spinner)
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
        return root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.endLocEditText.error = null
        binding.startLocEditText.error = null

        binding.findButton.setOnClickListener {
            if (validateForm()) {
                binding.endLocEditText.error = null
                binding.startLocEditText.error = null
                binding.statLocTextInput.error = null
                binding.endLocTextInput.error = null
                ChargeStationModel.addObserver(this)
                val csDataList : ListView = binding.cStationLV
                val csdata : ArrayList<ChargeStations> = ArrayList()
                csListAdapter =
                    thiscontext?.let { cStationListAdapter(it, R.layout.item_custom_row, csdata) }
                csDataList.adapter = csListAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

    override fun update(p0 : Observable?, p1 : Any?) {
        csListAdapter?.clear()
        val data = ChargeStationModel.getData()
        if (data != null) {
            //csListAdapter?.clear()
            csListAdapter?.addAll(data)
            csListAdapter?.notifyDataSetChanged()
        }

    }

    override fun onResume() {
        super.onResume()
        //ChargeStationModel.addObserver(this)
        binding.endLocEditText.error = null
        binding.startLocEditText.error = null
    }

    override fun onStop() {
        super.onStop()
        //ChargeStationModel.deleteObserver(this)
    }

    override fun onPause() {
        super.onPause()
        //ChargeStationModel.deleteObserver(this)
    }

    private fun validateForm() : Boolean {
        var valid = true

        val startloc = binding.startLocEditText.text.toString()
        if (TextUtils.isEmpty(startloc)) {
            binding.startLocEditText.error = "!"
            valid = false
        } else {

            binding.startLocEditText.error = null
        }

        if (startloc != "Melbourne") {
            binding.statLocTextInput.error = "Fixed Start location - please enter: Melbourne"
            valid = false
        } else {
            binding.startLocEditText.error = null
        }

        val endloc = binding.endLocEditText.text.toString()
        if (TextUtils.isEmpty(endloc)) {
            binding.endLocEditText.error = "!"
            valid = false
        } else {

            binding.endLocEditText.error = null
        }
        if (endloc != "Sydney") {
            binding.endLocTextInput.error = "Fixed End location - please enter: Sydney"
            valid = false
        } else {
            binding.endLocEditText.error = null
        }
        return valid
    }
}


