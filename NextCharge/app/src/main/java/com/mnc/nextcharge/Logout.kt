package com.mnc.nextcharge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mnc.nextcharge.databinding.FragmentLogoutBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class logout : Fragment(){
     private lateinit var auth : FirebaseAuth
    private var binding : FragmentLogoutBinding? = null
        override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        binding = FragmentLogoutBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    signOut()
    //binding!!.logoutbutton.setOnClickListener{signIn()}

    }

    private fun signIn()
    {findNavController().navigate(R.id.nextChargeLoginFragment)}

    private fun signOut() {
     auth.signOut()
    }

    companion object {
    // nothing
    }
}