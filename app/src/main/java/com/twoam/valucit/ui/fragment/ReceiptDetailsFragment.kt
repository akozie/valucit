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
import com.bumptech.glide.Glide
import com.twoam.valucit.R
import com.twoam.valucit.adapter.GenericRecyclerAdapter
import com.twoam.valucit.adapter.GenericRecyclerBindingInterface
import com.twoam.valucit.databinding.FragmentReceiptDetailsBinding
import com.twoam.valucit.model.dashboard.TransactionsModel


class ReceiptDetailsFragment : Fragment() {

    private lateinit var binding: FragmentReceiptDetailsBinding
    private lateinit var transactionList: ArrayList<TransactionsModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_receipt_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transactionList()
        recentTransactions()
    }

    private fun transactionList() {
        transactionList = arrayListOf()
        transactionList.add(TransactionsModel("Leather Buckle Flatform Sandals", "Product code: TO25054 BLACK", "£55.00", null, R.drawable.shoe_outline))
        transactionList.add(TransactionsModel("Leather Lace Up Star Trainers", "Product code: T838082 WHITE", "£70.00", null, R.drawable.shoe_outline))
        transactionList.add(TransactionsModel("Pure Cotton Cuffed Parachute Trousers", "Product code: T574359 FADED KHAKI", "£29.50", null, R.drawable.trouser_outline))
        transactionList.add(TransactionsModel("Floral Elasticated Waist Wide Leg Trousers", "Product code: T832101W IVORY MIX", "£59.00", null, R.drawable.trouser_outline))
        transactionList.add(TransactionsModel("JAEGER: Linen Blend Floral Tie Neck Midi...", "Product code: T976323E NEUTRAL", "£175.00", null, R.drawable.gown_outline))
        transactionList.add(TransactionsModel("Tweed Tailored Textured Blazer", "Product code: T592162J PINK", "£79.00", null, R.drawable.blazer_outline))
        transactionList.add(TransactionsModel("Pure Cotton Broderie Detail Top", "Product code: T412244C WHITE", "£17.50", null, R.drawable.shirt_outline))
        transactionList.add(TransactionsModel("Ribbed Sweetheart Neck Jumper", "Product code: T380258 BLACK", "£25.00", null, R.drawable.gown_outline))
        transactionList.add(TransactionsModel("Padded Plunge V-Neck Swim Dress", "Product code: T528944 BLACK", "£35.00", null, R.drawable.gown_outline))
    }

    private fun recentTransactions() {
        val bindingInterface = object : GenericRecyclerBindingInterface<TransactionsModel> {
            override fun bindData(item: TransactionsModel, view: View) {
                val name: TextView = view.findViewById(R.id.items_recent_transaction_name)
                val date: TextView = view.findViewById(R.id.items_recent_transaction_date)
                val amount: TextView = view.findViewById(R.id.items_recent_transaction_amount)
                val noOfItem: TextView = view.findViewById(R.id.items_recent_number_of_items)
                val image:ImageView = view.findViewById(R.id.items_recent_transaction_image)
                name.text = item.name
                date.text = item.date
                amount.text = item.amount
                noOfItem.text = item.noOfItems
                Glide.with(requireContext()).load(item.image).into(image)
            }
        }

        val adapter = GenericRecyclerAdapter(transactionList, R.layout.items_recent_transactions_details_layout, bindingInterface, null)
        binding.fragmentReceiptDetailsRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.fragmentReceiptDetailsRecyclerview.adapter = adapter
    }

}