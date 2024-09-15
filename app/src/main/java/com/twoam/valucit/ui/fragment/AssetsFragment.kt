package com.twoam.valucit.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.twoam.valucit.R
import com.twoam.valucit.adapter.GenericRecyclerAdapter
import com.twoam.valucit.adapter.GenericRecyclerBindingInterface
import com.twoam.valucit.databinding.FragmentAssetsBinding
import com.twoam.valucit.databinding.LayoutAddSavingsPlanBinding
import com.twoam.valucit.databinding.LayoutTargetSavingsPlanBinding
import com.twoam.valucit.model.dashboard.InvestmentsModel
import com.twoam.valucit.model.dashboard.SavingsPlanModel
import com.twoam.valucit.model.dashboard.TargetSavingsModel


class AssetsFragment : Fragment() {

    private lateinit var binding: FragmentAssetsBinding
    private lateinit var assetsList: ArrayList<InvestmentsModel>
    private lateinit var savingsPlanDialog: AlertDialog
    private lateinit var savingsPlanDialogBinding: LayoutAddSavingsPlanBinding
    private lateinit var targetSavingsPlanDialog: AlertDialog
    private lateinit var targetSavingsPlanDialogBinding: LayoutTargetSavingsPlanBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_assets, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        assetsList()
        assetsSetUp()

        showSavingsPlanDialog()
        showTargetPlanDialog()
    }

    private fun showTargetPlanDialog() {
        targetSavingsPlanDialogBinding =
            LayoutTargetSavingsPlanBinding.inflate(
                LayoutInflater.from(requireContext()),
                null,
                false
            )
                .apply {
                    lifecycleOwner = requireActivity()
                    executePendingBindings()
                }
//        targetSavingsPlanDialog = AlertDialog.Builder(requireContext())
//            .setView(targetSavingsPlanDialogBinding.root)
//            .setCancelable(false)
//            .create()

//        targetSavingsPlanDialogBinding.targetAddButton.setOnClickListener {
//            targetSavingsPlanDialog.dismiss()
//        }
        binding.addAssets.setOnClickListener {
            findNavController().navigate(R.id.action_assetsFragment_to_assetsBottomDialogFragment)
        }
    }

    private fun showSavingsPlanDialog() {
        savingsPlanDialogBinding =
            LayoutAddSavingsPlanBinding.inflate(LayoutInflater.from(requireContext()), null, false)
                .apply {
                    lifecycleOwner = requireActivity()
                    executePendingBindings()
                }
        savingsPlanDialog = AlertDialog.Builder(requireContext())
            .setView(savingsPlanDialogBinding.root)
            .setCancelable(false)
            .create()

        savingsPlanDialogBinding.addButton.setOnClickListener {
            savingsPlanDialog.dismiss()
        }
//        binding.addSavingsPlanButton.setOnClickListener {
//            savingsPlanDialog.show()
        }


    private fun assetsSetUp() {
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

        val adapter = GenericRecyclerAdapter(assetsList, R.layout.investments_item_layout, bindingInterface, null)
        binding.expensesRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.expensesRecyclerview.adapter = adapter
    }

    private fun assetsList() {
        assetsList = arrayListOf()
        assetsList.add(InvestmentsModel(R.drawable.home, "AIR BNB", "✅2,400", "272,000", "MONTHLY"))
        assetsList.add(InvestmentsModel(R.drawable.monetization_on, "ETFS", "✅136", "12,000", "MONTHLY"))
        assetsList.add(InvestmentsModel(R.drawable.home, "AIR BNB", "\uD83D\uDD3B96", "23,000", "YEARLY"))
    }

}