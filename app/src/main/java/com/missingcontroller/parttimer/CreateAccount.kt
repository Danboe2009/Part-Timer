package com.missingcontroller.parttimer

data class CreateAccount(
    val realm: String = "",
    val fName: String = "",
    val lName: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val emailVerified: Boolean = true
)
