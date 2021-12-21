package com.missingcontroller.parttimer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PartListAdapter(private val list: List<PartObject>, identifier: Int) :
  RecyclerView.Adapter<PartViewHolder>() {
  private val viewIdentifier = identifier

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return PartViewHolder(
      inflater,
      parent,
      viewIdentifier
    )
  }

  override fun onBindViewHolder(holder: PartViewHolder, position: Int) {
    val messages: PartObject = list[position]
    holder.bind(messages)
  }

  override fun getItemCount(): Int = list.size
}