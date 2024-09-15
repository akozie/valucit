package com.twoam.valucit.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.twoam.valucit.R
import com.twoam.valucit.adapter.GenericRecyclerAdapter
import com.twoam.valucit.adapter.GenericRecyclerBindingInterface
import com.twoam.valucit.databinding.FragmentHomeBinding
import com.twoam.valucit.model.dashboard.TransactionsModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var transactionList: ArrayList<TransactionsModel>
    private lateinit var topMerchantsList: ArrayList<TransactionsModel>
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transactionList()
        topMerchantsList()
        recentTransactions()
        topMerchants()

         tabLayout = binding.tabLayout

        // Add tabs with their text
        tabLayout.addTab(tabLayout.newTab().setText("Expenses"))
        tabLayout.addTab(tabLayout.newTab().setText("Income"))
        tabLayout.addTab(tabLayout.newTab().setText("Goals"))

        // Handle tab selection
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Perform actions based on the selected tab
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselection if needed
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselection if needed
            }
        })

        binding.addRecord.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_bottomDialogFragment)
        }
    }

    private fun transactionList() {
        transactionList = arrayListOf()
        transactionList.add(TransactionsModel("AIR BNB", "yesterday", "75,000.00", "",null))
        transactionList.add(TransactionsModel("AIR BNB", "yesterday", "75,000.00", "",null))
        transactionList.add(TransactionsModel("AIR BNB", "yesterday", "75,000.00", "",null))
        transactionList.add(TransactionsModel("AIR BNB", "yesterday", "75,000.00", "",null))
    }

    private fun recentTransactions() {
        val bindingInterface = object : GenericRecyclerBindingInterface<TransactionsModel> {
            override fun bindData(item: TransactionsModel, view: View) {
                val name: TextView = view.findViewById(R.id.items_name)
                val date: TextView = view.findViewById(R.id.item_date)
                val amount: TextView = view.findViewById(R.id.item_amount)
                name.text = item.name
                date.text = item.date
                amount.text = item.amount
            }
        }

        val adapter = GenericRecyclerAdapter(transactionList, R.layout.cashflow_layout, bindingInterface, null)
        binding.expensesRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.expensesRecyclerview.adapter = adapter
    }

    private fun topMerchants() {
        val bindingInterface = object : GenericRecyclerBindingInterface<TransactionsModel> {
            override fun bindData(item: TransactionsModel, view: View) {
                val name: TextView = view.findViewById(R.id.top_merchants_name)
                val amount: TextView = view.findViewById(R.id.top_merchants_amount)
                val noOfItem: TextView = view.findViewById(R.id.top_merchants_items)
                name.text = item.name
                amount.text = item.amount
                noOfItem.text = item.noOfItems
            }
        }

//        val adapter = GenericRecyclerAdapter(topMerchantsList, R.layout.top_merchants, bindingInterface, null)
//        binding.topMerchantsRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        binding.topMerchantsRecyclerview.adapter = adapter
    }

    private fun topMerchantsList() {
        topMerchantsList = arrayListOf()
        topMerchantsList.add(TransactionsModel("AIR BNB", "yesterday", "75,000.00", "",null))
        topMerchantsList.add(TransactionsModel("AIR BNB", "yesterday", "75,000.00", "",null))
        topMerchantsList.add(TransactionsModel("AIR BNB", "yesterday", "75,000.00", "",null))
        topMerchantsList.add(TransactionsModel("AIR BNB", "yesterday", "75,000.00", "",null))
        topMerchantsList.add(TransactionsModel("AIR BNB", "yesterday", "75,000.00", "",null))
        topMerchantsList.add(TransactionsModel("AIR BNB", "yesterday", "75,000.00", "",null))
    }
}