package com.paul.splitmoney.fragment

import android.os.Bundle
import android.service.autofill.UserData
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.paul.splitmoney.R
import com.paul.splitmoney.adapter.FriendsAdapters
import com.paul.splitmoney.adapter.GroupTypeAdapter
import com.paul.splitmoney.databinding.FragmentCreateGroupBinding
import com.paul.splitmoney.model.GroupDetailsModel
import com.paul.splitmoney.model.GroupTypeModel
import com.paul.splitmoney.viewmodel.GroupViewModel


class CreateGroupFragment : Fragment() {
    private var groupTypeList: ArrayList<GroupTypeModel> = arrayListOf(
        GroupTypeModel(R.drawable.home_icon1, "Home"),
        GroupTypeModel(R.drawable.air_icon, "Trip"),
        GroupTypeModel(R.drawable.love, "Couple"),
        GroupTypeModel(R.drawable.bill, "Others"),
    )
    private lateinit var groupTypeAdapter: GroupTypeAdapter
    private var _binding: FragmentCreateGroupBinding? = null
    private val binding get() = _binding!!

    private lateinit var firestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var groupViewModel: GroupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        groupViewModel = ViewModelProvider(this).get(GroupViewModel::class.java)
    }





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateGroupBinding.inflate(inflater, container, false)
        firestore = FirebaseFirestore.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()

        groupViewModel.allGroups.observe(viewLifecycleOwner) { groupList ->
            android.util.Log.d("RoomCheck", "All Groups in Room: $groupList")
        }



        implementGroupTypeAdapters()
        handleDoneClick()
        return binding.root
    }



    private fun implementGroupTypeAdapters() {
        groupTypeAdapter = GroupTypeAdapter(requireContext())
        binding.groupTypeRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = groupTypeAdapter
        }
        groupTypeAdapter.setGroupTypeList(groupTypeList)
    }

    private fun handleDoneClick() {
        binding.doneTextView.setOnClickListener {
            val groupName = binding.groupNameText.text.toString().trim()

            if (groupName.isEmpty()) {
                Toast.makeText(requireContext(), "Enter group name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedGroupType = groupTypeAdapter.getSelectedGroupType()
            if (selectedGroupType == null) {
                Toast.makeText(requireContext(), "Select a group type", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val currentUser = firebaseAuth.currentUser
            if (currentUser == null) {
                Toast.makeText(requireContext(), "User not logged in", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val groupId = firestore.collection("groups").document().id
          /*  val groupData = hashMapOf(
                "groupId" to groupId,
                "groupName" to groupName,
                "createdBy" to currentUser.uid,
                "groupType" to selectedGroupType.name
            )*/

            val groupData = GroupDetailsModel(
                groupId = groupId,
                groupName = groupName,
                createdBy = currentUser.uid,
                groupType = selectedGroupType.name
            )



            firestore.collection("groups").document(groupId)
                .set(groupData)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Group created successfully!", Toast.LENGTH_SHORT).show()
                    // Save locally in Room
                    groupViewModel.insertGroup(groupData)
                    // Optionally clear inputs or navigate back
                    binding.groupNameText.text.clear()
                    groupTypeAdapter.clearSelection()

                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, MainFragment())
                        .addToBackStack(null)
                        .commit()
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Failed to create group", Toast.LENGTH_SHORT).show()
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}