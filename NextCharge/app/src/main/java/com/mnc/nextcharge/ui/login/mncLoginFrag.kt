package com.mnc.nextcharge.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.mnc.nextcharge.R
import com.mnc.nextcharge.databinding.FragmentMncLoginBinding

class mncLoginFrag : Fragment() {

    private lateinit var tvRedirectSignUp : TextView
    lateinit var etEmail : EditText
    private lateinit var etPass : EditText
    lateinit var btnLogin : Button

    private val TAG = "mncLoginFrag"

    //private lateinit var loginViewModel : LoginViewModel
    private var _binding : FragmentMncLoginBinding? = null
    private val firebaseAuth = FirebaseAuth.getInstance()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // Creating firebaseAuth object
    lateinit var auth : FirebaseAuth


    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {
        _binding = FragmentMncLoginBinding.inflate(inflater, container, false)
        // initialising Firebase auth object
        auth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
        //  .get(LoginViewModel::class.java)
        // initialising Firebase auth object
        //auth = FirebaseAuth.getInstance()
        val usernameEditText = binding.userId
        val passwordEditText = binding.password
        val loginButton = binding.login
        val caButton = binding.creataccButton
        val loadingProgressBar = binding.loading

        caButton.setOnClickListener { findNavController().navigate(R.id.action_mncLoginFrag_to_userAccCreateFragment2)}
        //loginButton.setOnClickListener { findNavController().navigate(R.id.action_mncLoginFrag_to_homeFragment) }


        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(
                s : CharSequence,
                start : Int,
                count : Int,
                after : Int
            ) {
                // ignore
            }

        override fun onTextChanged(s : CharSequence, start : Int, before : Int, count : Int) {
                // ignore
            }

            override fun afterTextChanged(s : Editable) {
                /*loginViewModel.loginDataChanged(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )*/
            }
        }
        //usernameEditText.addTextChangedListener(afterTextChangedListener)
        //passwordEditText.addTextChangedListener(afterTextChangedListener)

        loginButton.setOnClickListener {
            val etEmail = binding.userId.toString()
            val etPass = binding.password.toString()
            login(etEmail,etPass)
            //loginButton.setOnClickListener { findNavController().navigate(R.id.action_mncLoginFrag_to_homeFragment) }
        }

        }

        /* private fun updateUiWithUser(model : LoggedInUserView) {
        val welcome = getString(R.string.welcome) + model.displayName
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
        //findNavController().navigate(R.id.action_mncLoginFrag_to_homeFragment)
    }*/

        private fun showLoginFailed(@StringRes errorString : Int) {
            val appContext = context?.applicationContext ?: return
            //Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

        private fun login(etEmail: String,etPass: String) {

            //val etEmail = binding.userId.toString()
            //val etPass = binding.password.toString()
            // calling signInWithEmailAndPassword(email, pass)
            // function using Firebase auth object
            // On successful response Display a Toast
            //---------------------------------<<

            auth.signInWithEmailAndPassword(etEmail, etPass)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(context, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        //updateUI(null)
                        //checkForMultiFactorFailure(task.exception!!)
                    }

                    if (!task.isSuccessful) {
                        binding.loginStatus.setText("Failed to Login with email di and pwd")
                    }
                    //hideProgressBar()
                }
        }







            ///---------------------------->>
            /*val result = auth.signInWithEmailAndPassword(etEmail,etPass)
            if (result.isSuccessful) {
                Toast.makeText(context, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(context, "Log In failed -- userid>"+etEmail+"||Pwd>"+etPass, Toast.LENGTH_SHORT).show() }*/
        //}
}




