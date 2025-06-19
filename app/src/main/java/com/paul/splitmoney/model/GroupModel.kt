package com.paul.splitmoney.model

import com.paul.splitmoney.R

data class GroupModel(
    val groupImg: Int = R.drawable.pic1,
    val groupName: String,
    val exp: String = "No expenses"
)
