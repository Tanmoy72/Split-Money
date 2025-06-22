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

class EqualUserAdapter(context: Context): RecyclerView.Adapter<EqualUserAdapter.EqualUserViewHolder>() {

    private var equalUserList: ArrayList<GroupTypeModel> = ArrayList()
    fun setEqualUserList(newList: ArrayList<GroupTypeModel>) {
        equalUserList = newList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EqualUserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_eually_user, parent, false)
        return EqualUserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return equalUserList.size
    }

    override fun onBindViewHolder(holder: EqualUserViewHolder, position: Int) {
        val currentItem = equalUserList[position]
        holder.img.setImageResource(currentItem.img)
        holder.name.text = currentItem.name
    }
    class EqualUserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val img = itemView.findViewById<ImageView>(R.id.equallyUserImg)
        val name = itemView.findViewById<TextView>(R.id.equallyUserTextView)
    }
}