package com.missingcontroller.parttimer

data class PartObject(
    val part: String = "",
    val mileage: String = "",
    val date: String = "",
    val description: String = "",
    val consumable: Boolean = false,
    val type: String = "",
    val owner: String = "",
    val id: String = "",
    val completed: Boolean = false
) {
//    override fun toString(): String =
//        "Name = $part Install Date: $date Install Mileage: $mileage Description: $description Completed: $completed"
}