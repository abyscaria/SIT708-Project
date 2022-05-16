package com.mnc.nextcharge

import android.content.Context
//import android.os.Bundle
import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
import android.view.View
//import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar


open class BaseFragment : Fragment() {
    private var progressBar: ProgressBar? = null

    fun setProgressBar(bar: ProgressBar) {
        progressBar = bar
    }

    fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar?.visibility = View.INVISIBLE
    }

    fun hideKeyboard(view: View) {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onStop() {
        super.onStop()
        hideProgressBar()
    }
}