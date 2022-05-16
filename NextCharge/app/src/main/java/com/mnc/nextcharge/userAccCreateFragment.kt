package com.mnc.nextcharge

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseApp.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DatabaseReference.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.mnc.nextcharge.databinding.FragmentMncLoginBinding
import com.mnc.nextcharge.databinding.FragmentUserAccCreateBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [userAccCreateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class userAccCreateFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1 : String? = null
    private var param2 : String? = null
    //private var mncdb : DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")
    private val TAG = "userAccCreateFragment"
    private var _binding : FragmentUserAccCreateBinding?  = null
   // This property is only valid between onCreateView and
   // onDestroyView.
    private val binding get() = _binding!!
    // create Firebase authentication object
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            setHasOptionsMenu(true)
        }
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
             val toast1 = Toast.makeText(context, "before"+usrEmail+fname+lname+"> pwd>"+usrpwd+"Conf>"+confpwd, Toast.LENGTH_LONG).show()
             signUpUser()
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
         * @return A new instance of fragment userAccCreateFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1 : String, param2 : String) =
            userAccCreateFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun signUpUser() {
        val email = binding.accUserid.text.toString()
        val pass = binding.accPwd.text.toString()
        val confirmPassword = binding.accConfirmpwd.text.toString()

        // check pass
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(context, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPassword) {
            Toast.makeText(context, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }
        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.
        val task = auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_LONG).show()
                    //updateUI(null)
                }
            }
        }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}
