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

class UnequalUserAdapter(context: Context): RecyclerView.Adapter<UnequalUserAdapter.UnequalUserViewHolder>() {

    private var unequalUserList: ArrayList<GroupTypeModel> = ArrayList()
    fun setUnequalUserList(newList: ArrayList<GroupTypeModel>) {
        unequalUserList = newList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnequalUserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_un_eually_user, parent, false)
        return UnequalUserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return unequalUserList.size
    }

    override fun onBindViewHolder(holder: UnequalUserViewHolder, position: Int) {
        val currentItem = unequalUserList[position]
        holder.img.setImageResource(currentItem.img)
        holder.name.text = currentItem.name
    }
    class UnequalUserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val img = itemView.findViewById<ImageView>(R.id.unEquallyUserImg)
        val name = itemView.findViewById<TextView>(R.id.unEquallyUserTextView)
    }
}