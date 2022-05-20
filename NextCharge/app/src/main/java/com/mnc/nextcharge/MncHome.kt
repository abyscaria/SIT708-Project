package com.mnc.nextcharge

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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



/**
 * A simple [Fragment] subclass.
 * Use the [MncHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class MncHome : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment with the ProductGrid theme
        val view = inflater.inflate(R.layout.fragment_mnc_home, container, false)

        // Set up the tool bar
        //(activity as AppCompatActivity).setSupportActionBar(view.app_bar)

        return view;
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null

    }
}