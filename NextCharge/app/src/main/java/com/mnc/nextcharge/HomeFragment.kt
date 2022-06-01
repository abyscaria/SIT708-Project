package com.mnc.nextcharge

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import com.mnc.nextcharge.databinding.ActivityNextChargeHomeBinding
import com.mnc.nextcharge.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    //private lateinit var bindingNCH : ActivityNextChargeHomeBinding
    // This property is only valid between onCreateView and
    // onDestroyView.
    private lateinit var savedStateHandle: SavedStateHandle
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val currentUserViewModel: HomeViewModel by activityViewModels()
    private lateinit var bindingNCH : ActivityNextChargeHomeBinding

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        Log.w(TAG, "on HomeFragment - onCreateView() >>")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        //savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        val textView : TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        val root : View = binding.root
       return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val toast = Toast.makeText(context, "on Home onViewCreated", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "on HomeFragment - onViewCreated() >>#1")
        /*if (currentUserViewModel.hasNoUserSet()) {
            savedStateHandle.set(LOGIN_SUCCESSFUL, false)
            val toast2 =
                Toast.makeText(context, "User not loggedIn..please Login", Toast.LENGTH_SHORT)
                    .show()
            Log.d(TAG, "on HomeFragment - onViewCreated() >># $")
           findNavController().navigate(R.id.nextChargeLoginFragment)
           currentUserViewModel.userEmail.observe(viewLifecycleOwner) {
               binding.userID.text = userEmail.toString()
           }
        }*/
    }

    /* override fun onDestroyView() {
          super.onDestroyView()
          _binding = null

      }*/

    companion object {
        private const val TAG = "HomeFragment"
        private const val RC_MULTI_FACTOR = 9005
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }


}