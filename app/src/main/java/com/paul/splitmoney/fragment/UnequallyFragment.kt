package com.paul.splitmoney.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paul.splitmoney.R
import com.paul.splitmoney.adapter.UnequalUserAdapter
import com.paul.splitmoney.model.GroupTypeModel


class UnequallyFragment : Fragment() {

    private var unequalUserAllList: ArrayList<GroupTypeModel> = arrayListOf(
        GroupTypeModel(R.drawable.pic1, "Tanmoy Paul"),
        GroupTypeModel(R.drawable.pic1, "User2"),
        GroupTypeModel(R.drawable.pic1, "User3"),

    )
    private lateinit var unequalUserAdapter: UnequalUserAdapter
    private lateinit var unEqualUserRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_unequally, container, false)
        unEqualUserRecyclerView = view.findViewById(R.id.unEqualUserRecyclerView)
        implementUnequalUserAdapter()

        return view

    }

    private fun implementUnequalUserAdapter() {
        unEqualUserRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            unequalUserAdapter = UnequalUserAdapter(requireContext())
            adapter = unequalUserAdapter
            unequalUserAdapter.setUnequalUserList(unequalUserAllList)

        }
    }


}