package com.twoam.valucit.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.twoam.valucit.R
import com.twoam.valucit.adapter.GenericRecyclerAdapter
import com.twoam.valucit.adapter.GenericRecyclerBindingInterface
import com.twoam.valucit.adapter.OnTransactionsListener
import com.twoam.valucit.databinding.FragmentCashFlowBinding
import com.twoam.valucit.model.dashboard.TransactionsModel

class CashFlowFragment : Fragment(){

    private lateinit var binding: FragmentCashFlowBinding
    private lateinit var transactionList: ArrayList<TransactionsModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cash_flow, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cashFlowLList()
        recentTransactions()

        binding.addRecord.setOnClickListener {
            findNavController().navigate(R.id.action_cashFlowFragment_to_bottomDialogFragment)
        }
    }

    private fun cashFlowLList() {
        transactionList = arrayListOf()
        transactionList.add(TransactionsModel("AIR BNB", "yesterday", "75,000.00", "",null))
        transactionList.add(TransactionsModel("AIR BNB", "yesterday", "75,000.00", "",null))
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

}