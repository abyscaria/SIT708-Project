package com.mnc.nextcharge

import android.R.string
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthMultiFactorException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mnc.nextcharge.databinding.ActivityMainBinding
import com.mnc.nextcharge.databinding.FragmentMncLoginBinding


class NextChargeLoginFragment : BaseFragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentMncLoginBinding? = null
    //private var _bindingM: ActivityMainBinding? = null
    private val binding: FragmentMncLoginBinding
        get() = _binding!!

    private lateinit var savedStateHandle: SavedStateHandle
    private val currentUserViewModel: HomeViewModel by activityViewModels()
    //val navController = findNavController()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMncLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle.set(LOGIN_SUCCESSFUL, false)
       // setProgressBar(binding.progressBar)

        // Buttons
        with (binding) {
            emailSignInButton.setOnClickListener {
                val email = binding.userEmail.text.toString()
                val password = binding.userPassword.text.toString()
                signIn(email, password)
            }
            emailCreateAccountButton.setOnClickListener {
                val email = binding.userEmail.text.toString()
                val password = binding.userPassword.text.toString()
                createAccount(email, password)
            }
            signOutButton.setOnClickListener { signOut() }
            verifyEmailButton.setOnClickListener { sendEmailVerification() }
            reloadButton.setOnClickListener { reload() }
        }
        Log.w(TAG, "onCreatedView - NextCharge Login ")
        // Initialize Firebase Auth
        auth = Firebase.auth
    }

   /* public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
              Log.d(TAG, "Onstart.....: during reaload ${auth.currentUser}")
        //findNavController().navigate(R.id.action_nextChargeLoginFragment2_to_nav_home)
          // reload()
        }*/


    private fun createAccount(email: String, password: String) {
        //savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle


        Log.d(TAG, "createAccount:$email")
        if (!validateForm()) {
            return
        }

        showProgressBar()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                    } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

                hideProgressBar()
            }
    }

    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn >>:$email")
        if (!validateForm()) {
            return
        }

        //showProgressBar()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val user  = auth.currentUser

                    Log.d(TAG, "signInWithEmail:success $email")
                    if (currentUserViewModel.hasNoUserSet()) {
                        currentUserViewModel.setUser(user.toString())
                        savedStateHandle.set(LOGIN_SUCCESSFUL, true)
                        findNavController().popBackStack()
                    }
                    updateUI(user)
                   //findNavController().navigate(R.id.action_nextChargeLoginFragment2_to_nav_home) //}
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                    checkForMultiFactorFailure(task.exception!!)
                }

                if (!task.isSuccessful) {
                    binding.status.setText(R.string.auth_failed)
                }
                hideProgressBar()
            }
    }

    private fun signOut() {
        auth.signOut()
        updateUI(null)
    }

    private fun sendEmailVerification() {
        // Disable button
        binding.verifyEmailButton.isEnabled = false

        // Send verification email
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener(requireActivity()) { task ->
                // Re-enable button
                binding.verifyEmailButton.isEnabled = true
                if (task.isSuccessful) {
                    Toast.makeText(context,
                        "Verification email sent to ${user.email} ",
                        Toast.LENGTH_SHORT).show()
                        savedStateHandle.set(LOGIN_SUCCESSFUL, true)
                        findNavController().popBackStack()
                       } else {
                    Log.e(TAG, "sendEmailVerification", task.exception)
                    Toast.makeText(context,
                        "Failed to send verification email.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun reload() {
        auth.currentUser!!.reload().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                updateUI(auth.currentUser)
                Toast.makeText(context, "Reload successful! ${auth.currentUser}", Toast.LENGTH_SHORT).show()
                //findNavController().navigate(R.id.action_emailLoginFragment2_to_nav_home)
            } else {
                Log.e(TAG, "reload", task.exception)
                Toast.makeText(context, "Failed to reload user....", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = binding.userEmail.text.toString()
        if (TextUtils.isEmpty(email)) {
            binding.userEmail.error = "Required."
            valid = false
        } else {
            binding.userEmail.error = null
        }

        val password = binding.userPassword.text.toString()
        if (TextUtils.isEmpty(password)) {
            binding.userPassword.error = "Required."
            valid = false
        } else {
            binding.userPassword.error = null
        }

        return valid
    }

    private fun updateUI(user: FirebaseUser? = null) {
        hideProgressBar()

        if (user != null) {
            ///binding.status.text = "${getString(R.string.emailpassword_status_fmt,user.email,user.isEmailVerified)}"
            binding.detail.text = user.toString()

            binding.emailPasswordButtons.visibility = View.GONE
            binding.emailPasswordFields.visibility = View.GONE
            binding.signedInButtons.visibility = View.VISIBLE

            if (user.isEmailVerified) {
                binding.verifyEmailButton.visibility = View.GONE
                //findNavController().navigate(R.id.action_emailLoginFragment2_to_nav_home)
                currentUserViewModel.userEmail.observe(viewLifecycleOwner) { userEmail ->
                    if (currentUserViewModel.hasNoUserSet()) {
                        currentUserViewModel.setUser(user.toString())
                        findNavController().popBackStack()
                        findNavController().navigate(R.id.nav_home)
                    }
                }
            } else {
                binding.verifyEmailButton.visibility = View.VISIBLE
                binding.signOutButton.visibility = View.GONE
               }
        } else {
            binding.status.setText(R.string.signed_out)
            binding.detail.text = null
            binding.icon.visibility = View.VISIBLE
            binding.emailPasswordButtons.visibility = View.VISIBLE
            binding.emailPasswordFields.visibility = View.VISIBLE
            binding.signedInButtons.visibility = View.GONE
        }
    }

    private fun checkForMultiFactorFailure(e: Exception) {
        // Multi-factor authentication with SMS is currently only available for
        // Google Cloud Identity Platform projects. For more information:
        // https://cloud.google.com/identity-platform/docs/android/mfa
        if (e is FirebaseAuthMultiFactorException) {
            Log.w(TAG, "multiFactorFailure", e)
            val resolver = e.resolver
            val args = bundleOf(
                //MultiFactorSignInFragment.EXTRA_MFA_RESOLVER to resolver,
                //MultiFactorFragment.RESULT_NEEDS_MFA_SIGN_IN to true
            )
            //findNavController().navigate(R.id.action_emailpassword_to_mfa, args)
        }
    }

    /*override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/

    companion object {
        private const val TAG = "NextChargeLoginFragment"
        private const val RC_MULTI_FACTOR = 9005
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }
}