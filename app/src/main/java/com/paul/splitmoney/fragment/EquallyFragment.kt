package com.paul.splitmoney.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paul.splitmoney.R
import com.paul.splitmoney.adapter.EqualUserAdapter
import com.paul.splitmoney.model.GroupTypeModel


class EquallyFragment : Fragment() {
    private var equalUserAllList: ArrayList<GroupTypeModel> = arrayListOf(
        GroupTypeModel(R.drawable.pic1, "Tanmoy Paul"),
        GroupTypeModel(R.drawable.pic1, "User2"),
        GroupTypeModel(R.drawable.pic1, "User3"),

    )
    private lateinit var equalUserAdapter: EqualUserAdapter
    private lateinit var equalUserRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_equally, container, false)
        equalUserRecyclerView = view.findViewById(R.id.equalUserRecyclerView)
        implementEqualUserAdapter()
        return view
    }

    private fun implementEqualUserAdapter() {
        equalUserRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            equalUserAdapter = EqualUserAdapter(requireContext())
            adapter = equalUserAdapter
            equalUserAdapter.setEqualUserList(equalUserAllList)

        }
    }


}