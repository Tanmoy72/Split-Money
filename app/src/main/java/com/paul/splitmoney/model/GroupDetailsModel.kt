package com.paul.splitmoney.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groups")
data class GroupDetailsModel(
    @PrimaryKey  val groupId: String = "",
    val createdBy: String = "",
    val groupName: String = "",
    val groupType: String = ""
)
