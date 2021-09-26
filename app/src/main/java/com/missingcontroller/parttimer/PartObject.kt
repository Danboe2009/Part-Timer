package com.missingcontroller.parttimer

data class PartObject(
    val part: String,
    val mileage: Int,
    val date: java.util.Date,
    val description: String,
    val consumable: Boolean,
    val type: String,
    val id: String,
    val completed: Boolean
) {
    override fun toString(): String =
        "Name = $part Install Date: $date Install Mileage: $mileage Description: $description Completed: $completed"
}