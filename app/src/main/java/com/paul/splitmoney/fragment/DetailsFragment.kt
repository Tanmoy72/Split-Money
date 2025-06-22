package com.paul.splitmoney.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.paul.splitmoney.R
import com.paul.splitmoney.adapter.FeaturesAdapters
import com.paul.splitmoney.model.FeaturesModel

class DetailsFragment : Fragment() {
    private var featuresList: ArrayList<FeaturesModel> = arrayListOf(
        FeaturesModel("Settle up "),
        FeaturesModel("Convert USD"),
        FeaturesModel("Balances"),
        FeaturesModel("Totals"),
        FeaturesModel("Charts"),
    )

    private lateinit var featuresAdapters: FeaturesAdapters
    private lateinit var featuresBtnRecyclerView: RecyclerView

    private lateinit var participantTextView: TextView
    private lateinit var groupNameTextView: TextView

    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        firestore = FirebaseFirestore.getInstance()
        featuresBtnRecyclerView = view.findViewById(R.id.featuresBtnRecyclerView)
        participantTextView = view.findViewById(R.id.participantListTextView)
        groupNameTextView = view.findViewById(R.id.groupNameTextView)

        val groupId = arguments?.getString("groupId")
        val groupName = arguments?.getString("groupName")

        groupNameTextView.text = groupName

        if (groupId != null) {
            fetchGroupDetails(groupId)
        }

        view.findViewById<TextView>(R.id.addExpTextView).setOnClickListener {
            val addExpFragment = AddExpenseFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, addExpFragment)
                .addToBackStack(null)
                .commit()
        }

        implementFeaturesAdapters()
        return view
    }

    private fun fetchGroupDetails(groupId: String) {
        firestore.collection("groups")
            .document(groupId)
            .get()
            .addOnSuccessListener { document ->
                val participants = document.get("participants") as? List<*>
                val createdBy = document.getString("createdBy")

                val nameList = mutableListOf<String>()

                // Fetch creator name
                if (createdBy != null) {
                    firestore.collection("users").document(createdBy)
                        .get()
                        .addOnSuccessListener { creatorDoc ->
                            val creatorName = creatorDoc.getString("name") ?: createdBy
                            nameList.add("👑 Creator: $creatorName")

                            // Fetch participant names
                            if (!participants.isNullOrEmpty()) {
                                var fetchedCount = 0
                                for (uid in participants) {
                                    firestore.collection("users").document(uid.toString())
                                        .get()
                                        .addOnSuccessListener { participantDoc ->
                                            val participantName = participantDoc.getString("name") ?: uid.toString()
                                            nameList.add("👤 $participantName")

                                            fetchedCount++
                                            if (fetchedCount == participants.size) {
                                                participantTextView.text = nameList.joinToString("\n")
                                            }
                                        }
                                }
                            } else {
                                participantTextView.text = nameList.joinToString("\n")
                            }
                        }
                }
            }
    }

    private fun implementFeaturesAdapters() {
        featuresBtnRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            featuresAdapters = FeaturesAdapters(requireContext())
            adapter = featuresAdapters
            featuresAdapters.setFeaturesList(featuresList)
        }
    }
}
