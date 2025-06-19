package com.paul.splitmoney.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.paul.splitmoney.model.GroupDetailsModel
import com.paul.splitmoney.room.AppDatabase
import kotlinx.coroutines.launch

class GroupViewModel(application: Application) : AndroidViewModel(application) {

    private val groupDao = AppDatabase.getDatabase(application).groupDao()
    val allGroups: LiveData<List<GroupDetailsModel>> = groupDao.getAllGroups()

    fun insertGroup(group: GroupDetailsModel) {
        viewModelScope.launch {
            groupDao.insertGroup(group)
        }
    }

    fun deleteGroup(group: GroupDetailsModel) {
        viewModelScope.launch {
            groupDao.deleteGroup(group)
        }
    }
}
