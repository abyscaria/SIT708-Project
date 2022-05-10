package com.mnc.nextcharge

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseApp.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DatabaseReference.*
import com.google.firebase.database.FirebaseDatabase
import com.mnc.nextcharge.databinding.FragmentUserAccCreateBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var _binding : FragmentUserAccCreateBinding? = null
/**
 * A simple [Fragment] subclass.
 * Use the [userAccCreateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class userAccCreateFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1 : String? = null
    private var param2 : String? = null
    private var mncdb : DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")

   // This property is only valid between onCreateView and
   // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            setHasOptionsMenu(true)
        }
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
             val usrid = hashCode().plus(100000)
             //mncdb = FirebaseApp.getInstance().getRefere
             val mncusr = createUserAcc(usrid.toString(), usrEmail, fname, lname, usrpwd)
             //val toast = Toast.makeText(applicationContext, "before"+usrid.toString()+usrEmail+fname+lname+usrpwd, duration).show()
             mncdb.child("Users").child(usrid.toString()).setValue(mncusr).addOnSuccessListener {
                 //al caMsg = findViewById<TextView>(R.id.caMessage)
                 val text = "Success!!"
                 binding.caMessage.text = "Account Created - Successfully!!!"
                 val toast = Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
                 // Clear on screen data ....
                 binding.accUserid.text.clear()
                 binding.accFname.text.clear()
                 binding.accLname.text.clear()
                 binding.accPwd.text.clear()
                 binding.accConfirmpwd.text.clear()
             }.addOnFailureListener {
                 val text1 = "Failed to create user"
                 val duration1 = Toast.LENGTH_SHORT
                 val toast = Toast.makeText(activity, text1, Toast.LENGTH_SHORT).show()
         }
            //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
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
    /*override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }*/
}