package com.mnc.nextcharge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome MyNextCharge Home"
    }
    val text : LiveData<String> = _text

    private val _userEmail = MutableLiveData<String>()
    val userEmail : LiveData<String> = _userEmail

     fun setUser(userEmail: String) {
        _userEmail.value = userEmail
    }

    fun resetUser() {
          _userEmail.value = ""

    }

    fun hasNoUserSet(): Boolean {
        return _userEmail.value.isNullOrEmpty()
    }

}