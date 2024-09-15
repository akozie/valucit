package com.twoam.valucit.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.twoam.valucit.R
import com.twoam.valucit.adapter.GenericRecyclerAdapter
import com.twoam.valucit.adapter.GenericRecyclerBindingInterface
import com.twoam.valucit.databinding.FragmentAccountBinding
import com.twoam.valucit.model.dashboard.AccountModel
import com.twoam.valucit.model.dashboard.InvestmentsModel


class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var accountList: ArrayList<AccountModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accountsList()
        accountsSetUp()
    }


    private fun accountsSetUp() {
        val bindingInterface = object : GenericRecyclerBindingInterface<AccountModel> {
            override fun bindData(item: AccountModel, view: View) {
                val amount: TextView = view.findViewById(R.id.amount)
                val name: TextView = view.findViewById(R.id.target_name)
                val targetAmount: TextView = view.findViewById(R.id.target_amount)
                val date: TextView = view.findViewById(R.id.calendar)
                val inflow: TextView = view.findViewById(R.id.in_out_text_view)
                amount.text = item.total_amount
                targetAmount.text = item.target_amount
                date.text = item.duration
                name.text = item.target_name
                inflow.text = item.inflow
            }
        }

        val adapter = GenericRecyclerAdapter(accountList, R.layout.accounts_item_layout, bindingInterface, null)
        binding.accountRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.accountRecyclerview.adapter = adapter
    }

    private fun accountsList() {
        accountList = arrayListOf()
        accountList.add(AccountModel(R.drawable.home, "PENSIONS", "IN", "+ 1,000", "42.5K", "\"MONTHLY"))
        accountList.add(AccountModel(R.drawable.home, "GTBANK SAVINGS", "IN/OUT", "+ 1,000", "42.5K", "" ))
        accountList.add(AccountModel(R.drawable.home, "PENSIONS", "IN/OUT", "+ 1,000", "42.5K", "MONTHLY"))
    }

}