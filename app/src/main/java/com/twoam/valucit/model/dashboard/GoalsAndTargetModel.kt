package com.twoam.valucit.model.dashboard

data class GoalsAndTargetModel(
    val image: Int,
    val target_name: String,
    val target_status: String?,
    val target_amount: String?,
    val target_date: String?,
)


data class InvestmentsModel(
    val image: Int,
    val target_name: String,
    val target_amount: String?,
    val total_amount: String?,
    val duration: String?,
)

data class AccountModel(
    val image: Int,
    val target_name: String,
    val inflow: String,
    val target_amount: String?,
    val total_amount: String?,
    val duration: String?,
)

data class CountryModel(
    val image: Int,
    val country_name: String,
)
data class CategoryModel(
    val category_name: String,
    val country_type: String,
)

data class Country(val flagResId: String, val name: String)
data class AllCurrency(val currencyID: String, val currencyName: String, val currencyAcronym: String)
