package com.missingcontroller.parttimer

import java.util.*

data class AddPartObject(
    val part: String = "",
    val mileage: String = "",
    val date: Date?,
    val description: String = "",
    val consumable: Boolean = false,
    val type: String = "",
    val owner: String = "",
    val completed: Boolean = false
) {
//    override fun toString(): String =
//        "Name = $part Install Date: $date Install Mileage: $mileage Description: $description Completed: $completed"
}