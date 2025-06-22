package com.paul.splitmoney.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paul.splitmoney.R
import com.paul.splitmoney.model.GroupModel

class GroupAdapters(context: Context, private val onItemClick: (groupId: String, groupName: String) -> Unit):RecyclerView.Adapter<GroupAdapters.GroupViewModel>() {

    private var groupList: ArrayList<GroupModel> = ArrayList()
    fun setGroupList(newList: ArrayList<GroupModel>) {
        groupList = newList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewModel {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_group, parent, false)
        return GroupViewModel(itemView)
    }

    override fun getItemCount(): Int {
        return groupList.size
    }

    override fun onBindViewHolder(holder: GroupViewModel, position: Int) {
      val currentItem = groupList[position]
        holder.group.setImageResource(currentItem.groupImg)
        holder.groupNames.text = currentItem.groupName
        holder.expensesText.text = currentItem.exp

        holder.itemView.setOnClickListener {
            onItemClick(currentItem.groupId, currentItem.groupName)
        }
    }

    class GroupViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val group = itemView.findViewById<ImageView>(R.id.groupImg)
        val groupNames = itemView.findViewById<TextView>(R.id.groupName)
        val expensesText = itemView.findViewById<TextView>(R.id.expensesTextView)

    }
}