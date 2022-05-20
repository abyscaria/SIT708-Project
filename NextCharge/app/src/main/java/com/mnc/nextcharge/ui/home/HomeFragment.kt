package com.mnc.nextcharge.ui.home

import android.os.Build.ID
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.mnc.nextcharge.R
import com.mnc.nextcharge.databinding.ActivityMainBinding
import com.mnc.nextcharge.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var auth : FirebaseAuth
    private var _binding : FragmentHomeBinding? = null
    private var _bindingm : ActivityMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val bindingm get() = _bindingm!!

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {

        val currentUsr = bindingm.userID


        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root : View = binding.root
        val textView : TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it   //auth.currentUser.toString()
        }
        return root
    }

   override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentUsr = auth.currentUser
    if (currentUsr != binding.userID.text)
    {
        Log.d(TAG, "Inside the user validation..")
        findNavController().navigate(R.id.action_nav_home_to_emailLoginFragment2)
    }
}
    override fun onStart() {
        super.onStart()
    }
    //override fun onDestroyView() {
      //  super.onDestroyView()
        //_binding = null
   // }


   /* fun onMapReady(googleMap: GoogleMap) {
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(0.0, 0.0))
                .title("Marker")
        )
    }*/
 fun authentcationStatus ()
   {

 }


}