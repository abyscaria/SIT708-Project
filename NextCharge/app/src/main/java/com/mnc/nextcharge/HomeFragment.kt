package com.mnc.nextcharge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mnc.nextcharge.databinding.FragmentHomeBinding
import java.net.NoRouteToHostException
import java.time.temporal.TemporalAdjusters.next

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val currentUserViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root : View = binding.root
        val textView : TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        if(currentUserViewModel.hasNoUserSet()) {
            val toast = Toast.makeText(context,"No user set must Login",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.nextChargeLoginFragment)
        }
       return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toast = Toast.makeText(context,"on Home onViewCreated",Toast.LENGTH_SHORT).show()

    }

    private fun showWelcomeMessage() {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}