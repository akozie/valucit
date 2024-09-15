package com.twoam.valucit.model.dashboard

data class SavingsPlanModel(
    val image: Int,
    val name: String,
    val monthlyAmount: String?,
    val totalAmountSaved: String
)
