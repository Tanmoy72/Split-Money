package com.paul.splitmoney.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.paul.splitmoney.R
import com.paul.splitmoney.adapter.GroupAdapters
import com.paul.splitmoney.databinding.FragmentHomeBinding
import com.paul.splitmoney.model.GroupModel
import com.paul.splitmoney.utils.DataPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var groupAdapter: GroupAdapters
    private var groupAllList: ArrayList<GroupModel> = ArrayList()

    private lateinit var firestore: FirebaseFirestore

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        firestore = FirebaseFirestore.getInstance()

        binding.createNewGroupText.setOnClickListener {
            val createGroupFragment = CreateGroupFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, createGroupFragment)
                .addToBackStack(null)
                .commit()
        }

        setupRecyclerView()
       // loadGroupsFromFirestore()
       // getGroupsForUser("yo0tu7cXs2ftD2jZNwvr1Z6OZlY2")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                loadGroupsFromFirestore()
            }catch (e: Exception){}

        }

        return binding.root
    }

    private fun setupRecyclerView() {
        groupAdapter = GroupAdapters(requireContext())
        binding.groupRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = groupAdapter
        }
    }

    private suspend fun loadGroupsFromFirestore() {
        val results1 = firestore.collection("groups")
            .whereEqualTo("createdBy", DataPreferences(requireContext()).getUserId().orEmpty())
            .get()
            .await()
            .documents

        val results2 = firestore.collection("groups")
            .whereArrayContains("participants", DataPreferences(requireContext()).getUserId().orEmpty())
            .get()
            .await()
            .documents
        results1.addAll(results2)
        groupAllList.clear()
        for (document in results1) {
            val groupName = document.getString("groupName") ?: continue
            val group = GroupModel(
                groupName = groupName,
                groupImg = R.drawable.pic1, // optional static image
                exp = "No expenses" // optional default value
            )
            groupAllList.add(group)
        }
        withContext(Dispatchers.Main){
            groupAdapter.setGroupList(groupAllList)
        }
    }

    fun getGroupsForUser(userId: String) {
        val db = FirebaseFirestore.getInstance()

        db.collection("groups")
            .whereArrayContains("participants", userId)
            .get()
            .addOnSuccessListener { querySnapshot ->

                groupAllList.clear()
                for (document in querySnapshot.documents) {
                    val groupName = document.getString("groupName") ?: continue
                    val group = GroupModel(
                        groupName = groupName,
                        groupImg = R.drawable.pic1, // optional static image
                        exp = "No expenses" // optional default value
                    )
                    groupAllList.add(group)
                }
                groupAdapter.setGroupList(groupAllList)
            }
            .addOnFailureListener { exception ->

            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
