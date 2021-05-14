package com.missingcontroller.parttimer

import java.util.*

data class PartObject(
  var name: String,
  val installDate: Date,
  val installMileage: Int
) {
  override fun toString(): String =
    "Name = $name Install Date: $installDate Install Mileage: $installMileage"
}