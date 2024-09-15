package com.twoam.valucit.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.twoam.valucit.R
import com.twoam.valucit.databinding.FragmentBottomDialogBinding


class BottomDialogFragment : BottomSheetDialogFragment() {

     private lateinit var binding: FragmentBottomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_dialog, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.incomeInflowTv.setOnClickListener {
            findNavController().navigate(R.id.action_bottomDialogFragment_to_incomeBottomDialogFragment)
        }
        binding.expenseOutflowTv.setOnClickListener {
            findNavController().navigate(R.id.action_bottomDialogFragment_to_expenseBottomDialogFragment)
        }
        binding.assetInvestmentTv.setOnClickListener {
            findNavController().navigate(R.id.action_bottomDialogFragment_to_assetsBottomDialogFragment)
        }
        binding.goalTargetTv.setOnClickListener {
            findNavController().navigate(R.id.action_bottomDialogFragment_to_goalsBottomDialogFragment)
        }
        binding.cancelDialog.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}