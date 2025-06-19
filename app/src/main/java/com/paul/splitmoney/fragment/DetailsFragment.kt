package com.paul.splitmoney.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paul.splitmoney.R
import com.paul.splitmoney.adapter.FeaturesAdapters
import com.paul.splitmoney.adapter.GroupAdapters
import com.paul.splitmoney.model.FeaturesModel


class DetailsFragment : Fragment() {
    private var featuresList: ArrayList<FeaturesModel> = arrayListOf(
        FeaturesModel("Settle up "),
        FeaturesModel("Convert USD"),
        FeaturesModel("Balances"),
        FeaturesModel("Totals"),
        FeaturesModel("Charts"),
    )
    private lateinit var FeaturesAdapters: FeaturesAdapters
    private lateinit var featuresBtnRecyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        featuresBtnRecyclerView = view.findViewById(R.id.featuresBtnRecyclerView)

        val addExpText = view.findViewById<TextView>(R.id.addExpTextView)
        addExpText.setOnClickListener {
            val homeFragment = AddExpenseFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, homeFragment)
                .addToBackStack(null)
                .commit()
        }

        implementFeaturesAdapters()
        return view
    }

    private fun implementFeaturesAdapters(){
        featuresBtnRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            FeaturesAdapters = FeaturesAdapters(requireContext())
            adapter = FeaturesAdapters
            FeaturesAdapters.setFeaturesList(featuresList)
        }
    }
}