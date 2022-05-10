package com.mnc.nextcharge

data class createUserAcc(val usrid:String?=null, val userEmail:String?=null, val fname:String?=null, val lname:String?=null, val userpwd:String?=null)
{
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}
