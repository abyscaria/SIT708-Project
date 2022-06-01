package com.mnc.nextcharge

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mnc.nextcharge.Adapters.cStationListAdapter
import com.mnc.nextcharge.DataModel.ChargeStationModel
import com.mnc.nextcharge.DataModel.ChargeStations
import com.mnc.nextcharge.databinding.FragmentSlideshowBinding
import java.util.*

class SlideshowFragment : Fragment(), Observer {
    // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding : FragmentSlideshowBinding? = null
    private val binding get() = _binding!!
    private var csListAdapter: cStationListAdapter? = null
    private var thiscontext : Context? = null
    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)
          thiscontext = container?.getContext();
          _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
          val root : View = binding.root
          return root
        }

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.findButton.setOnClickListener {
        ChargeStationModel.addObserver(this)
        val csDataList : ListView = binding.cStationRV
        val csdata : ArrayList<ChargeStations> = ArrayList()
        csListAdapter =
            thiscontext?.let { cStationListAdapter(it, R.layout.item_custom_row, csdata) }
        csDataList.adapter = csListAdapter
    }


}
    override fun onDestroyView() {
        super.onDestroyView()

    }

    override fun update(p0 : Observable?, p1 : Any?) {
       csListAdapter?.clear()
        val data = ChargeStationModel.getData()
        if(data != null ) {
            csListAdapter?.clear()
            csListAdapter?.addAll(data)
            csListAdapter?.notifyDataSetChanged()
        }

    }
    override fun onResume() {
        super.onResume()
        //ChargeStationModel.addObserver(this)
    }

    override fun onStop() {
        super.onStop()
        //ChargeStationModel.deleteObserver(this)
    }

    override fun onPause() {
        super.onPause()
        //ChargeStationModel.deleteObserver(this)
    }
}