package com.paul.splitmoney.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.paul.splitmoney.R

import com.paul.splitmoney.model.GroupTypeModel

class GroupTypeAdapter(private val context: Context) : RecyclerView.Adapter<GroupTypeAdapter.GroupTypeViewHolder>() {

    private var groupTypeList: ArrayList<GroupTypeModel> = ArrayList()
    private var selectedPosition: Int = -1

    fun setGroupTypeList(newList: ArrayList<GroupTypeModel>) {
        groupTypeList = newList
        notifyDataSetChanged()
    }
    fun getSelectedGroupType(): GroupTypeModel? {
        return if (selectedPosition != -1) groupTypeList[selectedPosition] else null
    }

    fun clearSelection() {
        selectedPosition = -1
        notifyDataSetChanged()
    }






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupTypeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_group_types, parent, false)
        return GroupTypeViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return groupTypeList.size
    }

    override fun onBindViewHolder(holder: GroupTypeViewHolder, position: Int) {
        val currentItem = groupTypeList[position]
        holder.groupTypeImg.setImageResource(currentItem.img)
        holder.groupTypeName.text = currentItem.name

        // Change background based on selection
        if (position == selectedPosition) {
            holder.itemView.findViewById<ConstraintLayout>(R.id.groupTypeLayout)
                .setBackgroundResource(R.drawable.text_bk_blue) // Selected background
        } else {
            holder.itemView.findViewById<ConstraintLayout>(R.id.groupTypeLayout)
                .setBackgroundResource(R.drawable.text_bk) // Default background
        }

        // Set click listener to update selected position
        holder.itemView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged() // Refresh the adapter
        }

    }
    class GroupTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val groupTypeImg = itemView.findViewById<ImageView>(R.id.groupTypeIcon)
        val groupTypeName = itemView.findViewById<TextView>(R.id.groupTypeText)


    }
}