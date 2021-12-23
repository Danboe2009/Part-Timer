package com.missingcontroller.parttimer

data class CreateAccountResponse(

    val realm: String,
    val username: String,
    val email: String,
    val emailVerified: Boolean,
    val id: String,
    val fName: String,
    val lName: String

)