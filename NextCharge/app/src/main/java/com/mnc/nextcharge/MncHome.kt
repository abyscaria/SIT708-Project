package com.mnc.nextcharge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.mnc.nextcharge.databinding.FragmentMncHomeBinding
import com.mnc.nextcharge.databinding.FragmentUserAccCreateBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var _binding : FragmentMncHomeBinding? = null
//private val mappsupport : SupportMapFragment ="com.google.android.gms.maps.SupportMapFragment"

/**
 * A simple [Fragment] subclass.
 * Use the [MncHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class MncHome : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1 : String? = null
    private var param2 : String? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_mnc_home, container, false)
        _binding = FragmentMncHomeBinding.inflate(inflater, container, false)
        /*val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap ->
            addMarkers(googleMap)
        }*/

        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*binding.openHomeButton.setOnClickListener {
            findNavController().navigate(R.id.action_mncHome_to_mncMenuFrag)
        }*/
        }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MncHome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1 : String, param2 : String) =
            MncHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
                /**
                 * Adds marker representations of the places list on the provided GoogleMap object
                 */

            }
    }
        /*private fun addMarkers(googleMap : GoogleMap) {
            places.forEach { place ->
                val marker = googleMap.addMarker(
                    MarkerOptions()
                        .title(place.name)
                        .position(place.latLng)
                )
            }
        }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}