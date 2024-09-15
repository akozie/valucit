package com.twoam.valucit.model.dashboard

data class TransactionsModel(
    val name: String,
    val date: String?,
    val amount: String,
    val noOfItems: String?,
    val image: Int?
)
