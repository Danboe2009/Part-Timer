package com.missingcontroller.parttimer.part

data class PartsResponse(
    val part: String = "",
    val mileage: Int = 0,
    val date: String = "",
    val description: String = "",
    val consumable: Boolean = false,
    val type: String = "",
    val completed: Boolean = false,
    val owner: String = "",
    val id: String = ""
)
