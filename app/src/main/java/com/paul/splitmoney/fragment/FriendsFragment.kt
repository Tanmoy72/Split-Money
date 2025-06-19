package com.paul.splitmoney.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paul.splitmoney.R
import com.paul.splitmoney.adapter.FriendsAdapters
import com.paul.splitmoney.adapter.GroupAdapters
import com.paul.splitmoney.model.FriendsModel


class FriendsFragment : Fragment() {
    private var friendsList: ArrayList<FriendsModel> = arrayListOf(
        FriendsModel("Tanmoy Paul", R.drawable.pic1),
        FriendsModel("Tanmoy Paul", R.drawable.pic1),
        FriendsModel("Tanmoy Paul", R.drawable.pic1),

    )
    private lateinit var friendsAdapters: FriendsAdapters
    private lateinit var friendsRecyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_friends, container, false)
        friendsRecyclerView = view.findViewById(R.id.friendsRecyclerView)

        implementFriendsAdapters()
        return view
    }
    private fun implementFriendsAdapters(){
        friendsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            friendsAdapters = FriendsAdapters(requireContext())
            adapter = friendsAdapters
            friendsAdapters.setFriendsList(friendsList)
        }
    }

}