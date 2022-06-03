package com.mnc.nextcharge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mnc.nextcharge.databinding.FragmentUserAccCreateBinding


class UserAccFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1 : String? = null
    private var param2 : String? = null

    //private var mncdb : DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")
    private val TAG = "UserAccFragment"
    private var _binding : FragmentUserAccCreateBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // create Firebase authentication object
    private lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialising auth object
        auth = Firebase.auth

    }

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        // Inflate the layout for this fragment
        _binding = FragmentUserAccCreateBinding.inflate(inflater, container, false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_user_acc_create, container, false)
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.accCreateBtn.setOnClickListener {
            val usrEmail = binding.accUserid.text.toString()
            val fname = binding.accFname.text.toString()
            val lname = binding.accLname.text.toString()
            val usrpwd = binding.accPwd.text.toString()
            val confpwd = binding.accConfirmpwd.toString()
            /* val usrid = hashCode().plus(100000)
             //mncdb = FirebaseApp.getInstance().getRefere
             val mncusr = createUserAcc(usrid.toString(), usrEmail, fname, lname, usrpwd) */
            val toast1 = Toast.makeText(
                context,
                "before" + usrEmail + fname + lname + "> pwd>" + usrpwd + "Conf>" + confpwd,
                Toast.LENGTH_LONG
            ).show()
            //mncdb.child("Users").child(usrid.toString()).setValue(mncusr).addOnSuccessListener {
            //al caMsg = findViewById<TextView>(R.id.caMessage)
            //val text = "Success!!"
            //binding.caMessage.text = "Account Created - Successfully!!!"
            //val toast2 = Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
            // Clear on screen data ....
        }
        //{
        //   val text1 = "Failed to create user"
        // val duration1 = Toast.LENGTH_SHORT
        //val toast = Toast.makeText(activity, text1, Toast.LENGTH_SHORT).show()
        //}
        //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserAccFragment.
         */
        // TODO: Rename and change types and number of parameters
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}
