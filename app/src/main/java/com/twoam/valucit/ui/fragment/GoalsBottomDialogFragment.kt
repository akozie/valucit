package com.twoam.valucit.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.twoam.valucit.R
import com.twoam.valucit.databinding.FragmentGoalsBottomDialogBinding


class GoalsBottomDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentGoalsBottomDialogBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_goals_bottom_dialog,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancelDialog.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}