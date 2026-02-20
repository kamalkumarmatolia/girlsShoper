package com.example.girlsshoper.domain.module

data class userModel(
    val firstName : String = "",
    val lastName : String = "",
    val email : String = "",
    val password : String = "",
    val phoneNumber : String = "",
    val adderss : String = "",
    val userImage : String = "",
    var uid : String = ""
)