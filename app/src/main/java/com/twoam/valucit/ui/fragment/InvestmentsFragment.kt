package com.twoam.valucit.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.twoam.valucit.R
import com.twoam.valucit.adapter.GenericRecyclerAdapter
import com.twoam.valucit.adapter.GenericRecyclerBindingInterface
import com.twoam.valucit.databinding.FragmentInvestmentsBinding
import com.twoam.valucit.model.dashboard.InvestmentsModel


class InvestmentsFragment : Fragment() {

    private lateinit var binding: FragmentInvestmentsBinding
    private lateinit var investmentsList: ArrayList<InvestmentsModel>
    private lateinit var newInvestmentDialog: AlertDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_investments, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        investmentList()
        investmentSetUp()

        binding.continueBtn.setOnClickListener {
//            findNavController().navigate(R.id.inv)
        }

        binding.addInvestmentBtn.setOnClickListener {
            findNavController().navigate(R.id.action_investmentsFragment_to_assetsBottomDialogFragment2)
        }
    }


    private fun investmentSetUp() {
        val bindingInterface = object : GenericRecyclerBindingInterface<InvestmentsModel> {
            override fun bindData(item: InvestmentsModel, view: View) {
                val img: ImageView = view.findViewById(R.id.target_img)
                val amount: TextView = view.findViewById(R.id.amount)
                val name: TextView = view.findViewById(R.id.target_name)
                val targetAmount: TextView = view.findViewById(R.id.target_amount)
                val date: TextView = view.findViewById(R.id.calendar)
                img.setBackgroundResource(item.image)
                amount.text = item.total_amount
                targetAmount.text = item.target_amount
                date.text = item.duration
                name.text = item.target_name
            }
        }

        val adapter = GenericRecyclerAdapter(
            investmentsList,
            R.layout.investments_item_layout,
            bindingInterface,
            null
        )
        binding.investmentsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.investmentsRecyclerView.adapter = adapter
    }

    private fun investmentList() {
        investmentsList = arrayListOf()
        investmentsList.add(
            InvestmentsModel(
                R.drawable.home,
                "AIR BNB",
                "✅2,400",
                "272,000",
                "MONTHLY"
            )
        )
        investmentsList.add(
            InvestmentsModel(
                R.drawable.monetization_on,
                "ETFS",
                "✅136",
                "12,000",
                "MONTHLY"
            )
        )
        investmentsList.add(
            InvestmentsModel(
                R.drawable.home,
                "AIR BNB",
                "\uD83D\uDD3B96",
                "23,000",
                "YEARLY"
            )
        )
    }

}