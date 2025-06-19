package com.paul.splitmoney.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.paul.splitmoney.model.GroupDetailsModel

@Dao
interface GroupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(group: GroupDetailsModel)

    @Query("SELECT * FROM groups")
    fun getAllGroups(): LiveData<List<GroupDetailsModel>>

    @Delete
    suspend fun deleteGroup(group: GroupDetailsModel)

    @Query("DELETE FROM groups WHERE createdBy = :createdBy")
    suspend fun deleteGroupByCreator(createdBy: String)
}
