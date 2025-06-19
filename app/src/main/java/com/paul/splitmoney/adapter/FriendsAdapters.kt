package com.paul.splitmoney.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paul.splitmoney.R
import com.paul.splitmoney.model.FriendsModel
import com.paul.splitmoney.model.GroupModel

class FriendsAdapters(context: Context):RecyclerView.Adapter<FriendsAdapters.FriendsViewModel>() {

    private var friendsList: ArrayList<FriendsModel> = ArrayList()
    fun setFriendsList(newList: ArrayList<FriendsModel>) {
        friendsList = newList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewModel {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_friends, parent, false)
        return FriendsViewModel(itemView)
    }

    override fun getItemCount(): Int {
        return friendsList.size
    }

    override fun onBindViewHolder(holder: FriendsViewModel, position: Int) {
      val currentItem = friendsList[position]
        holder.group.setImageResource(currentItem.friendImage)
        holder.groupNames.text = currentItem.friendName

    }

    class FriendsViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val group = itemView.findViewById<ImageView>(R.id.friendsImg)
        val groupNames = itemView.findViewById<TextView>(R.id.friendsNameTextView)


    }
}