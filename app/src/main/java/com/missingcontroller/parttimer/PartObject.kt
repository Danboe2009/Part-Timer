package com.missingcontroller.parttimer

import java.util.*

data class PartObject(
    val part: String = "",
    val mileage: Int = 0,
    val date: String = "",
    val description: String = "",
    val consumable: Boolean = false,
    val type: String = "",
    val id: String = "",
    val completed: Boolean = false
) {
    override fun toString(): String =
        "Name = $part Install Date: $date Install Mileage: $mileage Description: $description Completed: $completed"
}