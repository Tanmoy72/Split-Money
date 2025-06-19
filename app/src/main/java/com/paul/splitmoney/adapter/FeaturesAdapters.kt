package com.paul.splitmoney.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paul.splitmoney.R
import com.paul.splitmoney.model.FeaturesModel
import com.paul.splitmoney.model.FriendsModel
import com.paul.splitmoney.model.GroupModel

class FeaturesAdapters(context: Context):RecyclerView.Adapter<FeaturesAdapters.FeaturesViewModel>() {

    private var featuresList: ArrayList<FeaturesModel> = ArrayList()
    fun setFeaturesList(newList: ArrayList<FeaturesModel>) {
        featuresList = newList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturesViewModel {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_features, parent, false)
        return FeaturesViewModel(itemView)
    }

    override fun getItemCount(): Int {
        return featuresList.size
    }

    override fun onBindViewHolder(holder: FeaturesViewModel, position: Int) {
      val currentItem = featuresList[position]

        holder.groupNames.text = currentItem.name

    }

    class FeaturesViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val groupNames = itemView.findViewById<TextView>(R.id.featuresTextView)


    }
}