package com.missingcontroller.parttimer.part

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.missingcontroller.parttimer.R
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
    var statusCircle: ImageView = itemView.findViewById(R.id.iv_status_circle)

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    fun bind(partObject: PartObject) {
        name.text = partObject.part
        installDate.text = processDate(partObject.date)?.let { partDate(it) }
        installMilage.text = partObject.mileage.toString()
        consumable.text = processConsumable(partObject.consumable)
        typeOfPart.text = partObject.type
        daysSince.text = "${processDate(partObject.date)?.let { partAge(it) }} Days"
        processDate(partObject.date)?.let { partAge(it) }?.let { colorObject(it) }
    }

    private fun partAge(date: Date): Long {
        val difference: Long = Date().getTime() - date.getTime()
        return TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
    }

    private fun partDate(date: Date): String {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return "${calendar.get(Calendar.MONTH) + 1}/" +
                "${calendar.get(Calendar.DATE)}/" +
                "${calendar.get(Calendar.YEAR)}"
    }


    private fun colorObject(days: Long) {

        if (days > 365) {
            statusCircle.setColorFilter(
                itemView.resources.getColor(R.color.status_red),
                PorterDuff.Mode.SRC_ATOP
            )
        } else if (days > 100) {
            statusCircle.setColorFilter(
                itemView.resources.getColor(R.color.status_yellow),
                PorterDuff.Mode.SRC_ATOP
            )
        } else {
            statusCircle.setColorFilter(
                itemView.resources.getColor(R.color.status_green),
                PorterDuff.Mode.SRC_ATOP
            )
        }
    }

    private fun processConsumable(con: Boolean): String {
        return if (con) {
            "Consumable"
        } else {
            "Not Consumable"
        }
    }

    private fun processDate(date: String): Date? {
        if (date.isNotEmpty()) {
            return SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(date)
        }
        return null
    }
}