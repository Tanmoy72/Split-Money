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
import com.paul.splitmoney.adapter.PaidByAdapter
import com.paul.splitmoney.model.GroupTypeModel


class PaidByFragment : Fragment() {
    private var padByAllList: ArrayList<GroupTypeModel> = arrayListOf(
        GroupTypeModel(R.drawable.pic1, "Tanmoy Paul"),
        GroupTypeModel(R.drawable.pic1, "User2"),
        GroupTypeModel(R.drawable.pic1, "User3"),

    )
    private lateinit var padByAdapter: PaidByAdapter
    private lateinit var padByRecyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_paid_by, container, false)
        padByRecyclerView = view.findViewById(R.id.padByRecyclerView)

        implementPadByAdapter()
        return view
    }

    private fun implementPadByAdapter() {
        padByRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            padByAdapter = PaidByAdapter(requireContext())
            adapter = padByAdapter
            padByAdapter.setPaidByList(padByAllList)

        }
    }


}