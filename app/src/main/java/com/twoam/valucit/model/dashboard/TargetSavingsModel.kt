package com.twoam.valucit.model.dashboard

data class TargetSavingsModel(
    val name: String,
    val estimate: String,
    val targetAmount:String,
    val savedAmount: String,
    val percentage: String
)
