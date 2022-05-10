package com.mnc.nextcharge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.databinding.DataBindingUtil.setContentView
import com.google.firebase.FirebaseApp.getInstance
import com.google.firebase.database.DatabaseReference
import com.mnc.nextcharge.databinding.FragmentSecondBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"



/**
 * A simple [Fragment] subclass.
 * Use the [CreateAccountFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class createAccountFrag : Fragment() {
    // TODO: Rename and change types of parameters
    //private var _binding : CreateAccountFrag? = null
    private lateinit var mncdb : DatabaseReference

    // This property is only valid between onCreateView and
    // onDestroyView.
    //private val cabind get() = _binding!!

    /*override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {

        //_binding = cabind(inflater, container, false)
       // return cabind.root

    }*/

/*
    //private lateinit var binding: ActivityCreateAccountBinding

    override fun onCreated(savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cabind.accCreateBtn.setOnClickListener {
            val usrEmail = cabind.text.toString()
            val fname = cabind.mncUserfname.text.toString()
            val lname = cabind.mncUserlname.text.toString()
            val usrpwd = cabind.mncUserpwd.text.toString()
            val usrid = hashCode()
            mncdb = getInstance().getReference("Users")
            val mncusr = CreateUserfbase(usrid.toString(), usrEmail, fname, lname, usrpwd)
            //val toast = Toast.makeText(applicationContext, "before"+usrid.toString()+usrEmail+fname+lname+usrpwd, duration).show()
            mncdb.child("Users").child(usrid.toString()).setValue(mncusr).addOnSuccessListener {
                val caMsg = findViewById<TextView>(R.id.caMessage)
                val text = "Success!!"
                val duration = Toast.LENGTH_SHORT
                caMsg.text = "Account Created - Successfully!!!"
                val toast = Toast.makeText(applicationContext, text, duration).show()
                // Clear on screen data ....
                binding.mncUserid.text.clear()
                binding.mncUserfname.text.clear()
                binding.mncUserlname.text.clear()
                binding.mncUserpwd.text.clear()
            }.addOnFailureListener {
                val text1 = "Failed to create user"
                val duration1 = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text1, duration1).show()
            }
        }

    }*/

}