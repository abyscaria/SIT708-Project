package com.mnc.nextcharge

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mnc.nextcharge.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    // [START declare_auth]
    private lateinit var auth : FirebaseAuth

    // [END declare_auth]
    private lateinit var binding : ActivitySignInBinding
    var sinorlog : String? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_sign_in)
        // Initialize Firebase Auth
        auth = Firebase.auth
        // [END initialize_auth]
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val currentUser = auth.currentUser
        sinorlog = getString(R.string.login_button_text)
        binding.multiButton.visibility = View.VISIBLE
        binding.multiButton.text = getString(R.string.login_button_text)
        binding.loginstatus.text = getString(R.string.prelogin_status)


        Log.i(TAG, "on Signin actvity before auth initiallize >>#1 ")
        //Log.i(TAG,"authentication status "+ auth.currentUser)
        binding.multiButton.setOnClickListener {
            val userEmail : String = binding.emailEditText.text.toString()
            val password : String = binding.passwordEditText.text.toString()

            if (sinorlog == "LOGIN") {
                Log.i(TAG, "on Signin actvity before auth initiallize >>#2 " + sinorlog)
                signIn(userEmail, password)
            }

            if (sinorlog == "CREATE ACCOUNT") {
                Log.i(TAG, "on Signin actvity before auth initiallize >>#3 " + sinorlog)
                createAccount(userEmail, password)
            }

            if (sinorlog == "VERIFY EMAIL") {
                Log.i(TAG, "on Signin actvity before auth initiallize >>#3 " + sinorlog)
                sendEmailVerification()
            }


        }
        binding.reqCreatAcc.setOnClickListener {
            binding.multiButton.visibility = View.VISIBLE
            binding.multiButton.text = getString(R.string.signup_button_text)
            binding.reqCreatAcc.visibility = View.GONE
            binding.loginstatus.text = null
            binding.emailEditText.text = null
            binding.passwordEditText.text = null
            sinorlog = getString(R.string.signup_button_text)
        }
    }

    // [START on_start_check_user]
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }

    }
    // [END on_start_check_user]

    private fun createAccount(email : String, password : String) {
        // [START create_user_with_email]
        if (!validateForm()) {
            return
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    binding.loginstatus.text = "Sucessfully Created - User Account"
                    sinorlog = getString(R.string.verify_button_text)
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Create Account Failed....",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.loginstatus.text = task.exception.toString()
                    updateUI(null)
                }
            }
        // [END create_user_with_email]
    }

    private fun signIn(email : String, password : String) {
        // [START sign_in_with_email]

        if (!validateForm()) {
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    //updateUI(user)
                    val intent = Intent(this, NextChargeHome::class.java).apply {}
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    binding.loginstatus.text = getString(R.string.login_failure)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.reqCreatAcc.text = getString(R.string.sinupOption_text)
                    binding.reqCreatAcc.visibility = View.VISIBLE
                    updateUI(null)
                }
            }
        // [END sign_in_with_email]
    }

    private fun sendEmailVerification() {
        // [START send_email_verification]
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                val intent = Intent(this, NextChargeHome::class.java).apply {}
                startActivity(intent)
            }

    }

    private fun updateUI(user : FirebaseUser? = null) {


        if (user != null) {
            //binding.status.text = user.email
            //binding.detail.text = user.toString()

            //binding.emailPasswordButtons.visibility = View.GONE
            //binding.emailPasswordFields.visibility = View.GONE
            binding.multiButton.visibility = View.GONE


            if (user.isEmailVerified) {
                sinorlog = getString(R.string.login_button_text)
                binding.multiButton.visibility = View.VISIBLE
                binding.multiButton.text = getString(R.string.login_button_text)
                binding.loginstatus.text = getString(R.string.prelogin_status)

            } else {
                binding.multiButton.visibility = View.VISIBLE
                binding.multiButton.text = getString(R.string.verify_button_text)
                binding.emailEditText.text = null
                binding.passwordEditText.text = null
                sinorlog = getString(R.string.verify_button_text)
                binding.reqCreatAcc.text = null
                binding.loginstatus.text = getString(R.string.verify_emailsend)
            }
        } else {
            //binding.status.setText(R.string.signed_out)
            //binding.detail.text = null
            //binding.icon.visibility = View.VISIBLE
            // binding.emailPasswordButtons.visibility = View.VISIBLE
            //binding.emailPasswordFields.visibility = View.VISIBLE
            //binding.signedInButtons.visibility = View.GONE
        }
    }


    private fun reload() {

    }

    private fun validateForm() : Boolean {
        var valid = true

        val email = binding.emailEditText.text.toString()
        if (TextUtils.isEmpty(email)) {
            binding.emailEditText.error = "Required."
            valid = false
        } else {
            binding.emailEditText.error = null
        }

        val password = binding.passwordEditText.text.toString()
        if (TextUtils.isEmpty(password)) {
            binding.passwordEditText.error = "Required."
            valid = false
        } else {
            binding.passwordEditText.error = null
        }

        return valid
    }


    companion object {
        private const val TAG = "SignInActivity"
    }
}



