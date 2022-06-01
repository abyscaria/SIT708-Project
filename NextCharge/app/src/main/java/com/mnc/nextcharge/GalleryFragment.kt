package com.mnc.nextcharge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.installations.Utils
import com.mnc.nextcharge.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding : FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

   override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root : View = binding.root

        /*val textView : TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }
 /*   override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.fragment_gallery)

    val rootView = findViewById<View>(android.R.id.content)

    val textInputLayouts = Utils.findViewsWithType(
        rootView, TextInputLayout::class.java)

    save_button.setOnClickListener {
        var noErrors = true
        for (textInputLayout in textInputLayouts) {
            val editTextString = textInputLayout.editText!!.text.toString()
            if (editTextString.isEmpty()) {
                textInputLayout.error = resources.getString(R.string.error_string)
                noErrors = false
            } else {
                textInputLayout.error = null
            }
        }

        if (noErrors) {
            // All fields are valid!
        }
    }
}*/



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}