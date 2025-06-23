package com.paul.splitmoney.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paul.splitmoney.R
import com.paul.splitmoney.model.GroupTypeModel

class PaidByAdapter(context: Context): RecyclerView.Adapter<PaidByAdapter.PaidByViewHolder>() {

    private var padByList: ArrayList<GroupTypeModel> = ArrayList()
    fun setPaidByList(newList: ArrayList<GroupTypeModel>) {
        padByList = newList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaidByViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_paid_by, parent, false)
        return PaidByViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return padByList.size
    }

    override fun onBindViewHolder(holder: PaidByViewHolder, position: Int) {
        val currentItem = padByList[position]
        holder.img.setImageResource(currentItem.img)
        holder.name.text = currentItem.name
    }
    class PaidByViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val img = itemView.findViewById<ImageView>(R.id.paidImg)
        val name = itemView.findViewById<TextView>(R.id.paidByNameTextView)
    }
}