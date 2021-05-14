package com.missingcontroller.parttimer

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class PartViewHolder(inflater: LayoutInflater, parent: ViewGroup, identifier: Int) :
  RecyclerView.ViewHolder(inflater.inflate(R.layout.part_object, parent, false)),
  View.OnClickListener {

  var name: TextView = itemView.findViewById(R.id.tv_part_name)
  var installDate: TextView = itemView.findViewById(R.id.tv_install_date)
  var installMilage: TextView = itemView.findViewById(R.id.tv_install_mileage)
  var daysSince: TextView = itemView.findViewById(R.id.tv_days_since)
  var constraintLayout: ConstraintLayout = itemView.findViewById(R.id.cl_part_object)

  override fun onClick(v: View?) {
    TODO("Not yet implemented")
  }

  fun bind(partObject: PartObject) {
    name.text = partObject.name
    installDate.text = formatDate(partObject.installDate)
    installMilage.text = partObject.installMileage.toString()
    daysSince.text = "${partAge(partObject.installDate)} Days"
    colorObject(partAge(partObject.installDate))
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
    if (days > 365) {
      constraintLayout.setBackgroundColor(Color.RED)
    } else if (days > 100) {
      constraintLayout.setBackgroundColor(Color.YELLOW)
    } else {
      constraintLayout.setBackgroundColor(Color.GREEN)
    }
  }
}