package com.missingcontroller.parttimer

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class PartViewHolder(inflater: LayoutInflater, parent: ViewGroup, identifier: Int) :
  RecyclerView.ViewHolder(inflater.inflate(R.layout.part_object, parent, false)),
  View.OnClickListener {

  var name: TextView = itemView.findViewById(R.id.tv_part_name)
  var installDate: TextView = itemView.findViewById(R.id.tv_install_date)
  var installMilage: TextView = itemView.findViewById(R.id.tv_install_mileage)
  var consumable: TextView = itemView.findViewById(R.id.tv_consumable)
  var typeOfPart: TextView = itemView.findViewById(R.id.tv_type_of_part)
  var daysSince: TextView = itemView.findViewById(R.id.tv_days_since)
  var constraintLayout: ConstraintLayout = itemView.findViewById(R.id.cl_part_object)

  override fun onClick(v: View?) {
    TODO("Not yet implemented")
  }

  fun bind(partObject: PartObject) {
    name.text = partObject.part
//    installDate.text = formatDate(partObject.date)
    installMilage.text = partObject.mileage.toString()
    consumable.text = processConsumable(partObject.consumable)
    typeOfPart.text = partObject.type
//    daysSince.text = "${partAge(partObject.date)} Days"
//    colorObject(partAge(partObject.date))
  }

  fun formatDate(date: Date): String {
    val pattern = "MM-dd-yyyy"
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.US)
    return simpleDateFormat.format(date)
  }

  fun partAge(date: Date): Long {
    val difference: Long = Date().getTime() - date.getTime()
    return TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
  }

  fun colorObject(days: Long) {
    val redColor = intArrayOf(Color.parseColor("#ff5858"), Color.parseColor("#ffc8c8"))
    val yellowColor = intArrayOf(Color.parseColor("#f2db00"), Color.parseColor("#f9ffa1"))
    val greenColor = intArrayOf(Color.parseColor("#5cb270"), Color.parseColor("#d1fbd8"))

    val redGD = GradientDrawable(GradientDrawable.Orientation.TL_BR, redColor)
    val yellowGD = GradientDrawable(GradientDrawable.Orientation.TL_BR, yellowColor)
    val greenGD = GradientDrawable(GradientDrawable.Orientation.TL_BR, greenColor)

    if (days > 365) {
      constraintLayout.setBackgroundDrawable(redGD)
    } else if (days > 100) {
      constraintLayout.setBackgroundDrawable(yellowGD)
    } else {
      constraintLayout.setBackgroundDrawable(greenGD)
    }
  }

  fun processConsumable(con: Boolean) : String{
    if (con){
      return "Cosumable"
    } else {
      return "Not Consumable"
    }
  }
}