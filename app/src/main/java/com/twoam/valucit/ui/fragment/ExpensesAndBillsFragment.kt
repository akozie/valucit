package com.twoam.valucit.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.twoam.valucit.R
import com.twoam.valucit.databinding.FragmentExpensesAndBillsBinding
import com.twoam.valucit.utils.SwitchColorState


class ExpensesAndBillsFragment : Fragment() {

    private lateinit var binding: FragmentExpensesAndBillsBinding
    private lateinit var switchState: SwitchColorState
    private lateinit var groceriesAmount: String
    private lateinit var selectedOption: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_expenses_and_bills, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.continueBtn.setOnClickListener {
            findNavController().navigate(R.id.action_expensesAndBillsFragment_to_goalsAndTargetsFragment)
        }


        switchState = SwitchColorState(requireContext())

        binding.groceries.thumbTintList = switchState.thumbStates
        binding.groceries.trackTintList = switchState.trackStates

        binding.utitlityBills.thumbTintList = switchState.thumbStates
        binding.utitlityBills.trackTintList = switchState.trackStates

        binding.transportation.thumbTintList = switchState.thumbStates
        binding.transportation.trackTintList = switchState.trackStates

        binding.insurance.thumbTintList = switchState.thumbStates
        binding.insurance.trackTintList = switchState.trackStates

        binding.entertainment.thumbTintList = switchState.thumbStates
        binding.entertainment.trackTintList = switchState.trackStates

        binding.investmentSavings.thumbTintList = switchState.thumbStates
        binding.investmentSavings.trackTintList = switchState.trackStates

        binding.charityReligious.thumbTintList = switchState.thumbStates
        binding.charityReligious.trackTintList = switchState.trackStates

        binding.other.thumbTintList = switchState.thumbStates
        binding.other.trackTintList = switchState.trackStates

        // List of TextView IDs that are common across all dialogs
        val commonTextViewIds = listOf(R.id.amount)


        setupSwitchListener(
            binding.groceries,
            R.layout.income_inflow_layout,
            commonTextViewIds
        ) { retrievedTexts, selectedOption ->
            groceriesAmount = "${retrievedTexts[0]} - $selectedOption"
            Log.d("SALARY_AMOUNT", groceriesAmount)
        }


        setupSwitchListener(
            binding.utitlityBills,
            R.layout.income_inflow_layout,
            commonTextViewIds
        ) { retrievedTexts, selectedOption ->
            groceriesAmount = "${retrievedTexts[0]} - $selectedOption"
            Log.d("SALARY_AMOUNT", groceriesAmount)
        }


        setupSwitchListener(
            binding.transportation,
            R.layout.income_inflow_layout,
            commonTextViewIds
        ) { retrievedTexts, selectedOption ->
            groceriesAmount = "${retrievedTexts[0]} - $selectedOption"
            Log.d("SALARY_AMOUNT", groceriesAmount)
        }


        setupSwitchListener(
            binding.insurance,
            R.layout.income_inflow_layout,
            commonTextViewIds
        ) { retrievedTexts, selectedOption ->
            groceriesAmount = "${retrievedTexts[0]} - $selectedOption"
            Log.d("SALARY_AMOUNT", groceriesAmount)
        }


        setupSwitchListener(
            binding.entertainment,
            R.layout.income_inflow_layout,
            commonTextViewIds
        ) { retrievedTexts, selectedOption ->
            groceriesAmount = "${retrievedTexts[0]} - $selectedOption"
            Log.d("SALARY_AMOUNT", groceriesAmount)
        }


        setupSwitchListener(
            binding.investmentSavings,
            R.layout.income_inflow_layout,
            commonTextViewIds
        ) { retrievedTexts, selectedOption ->
            groceriesAmount = "${retrievedTexts[0]} - $selectedOption"
            Log.d("SALARY_AMOUNT", groceriesAmount)
        }


        setupSwitchListener(
            binding.charityReligious,
            R.layout.income_inflow_layout,
            commonTextViewIds
        ) { retrievedTexts, selectedOption ->
            groceriesAmount = "${retrievedTexts[0]} - $selectedOption"
            Log.d("SALARY_AMOUNT", groceriesAmount)
        }
        setupSwitchListener(
            binding.other,
            R.layout.income_inflow_layout,
            commonTextViewIds
        ) { retrievedTexts, selectedOption ->
            groceriesAmount = "${retrievedTexts[0]} - $selectedOption"
            Log.d("SALARY_AMOUNT", groceriesAmount)
        }

    }


    private fun showDialog(
        switch: SwitchCompat,
        dialogLayoutId: Int,
        textViewIds: List<Int>,
        onTextsRetrieved: (List<String>, String) -> Unit
    ) {
        val dialogView = LayoutInflater.from(requireContext()).inflate(dialogLayoutId, null)
        val alertDialog = AlertDialog.Builder(requireContext()).setView(dialogView).create()

        // Initialize the TextViews
        val textViews = textViewIds.map { dialogView.findViewById<TextView>(it) }

        // Initialize the Spinner
        val spinner = dialogView.findViewById<AutoCompleteTextView>(R.id.recurring_tenor)
        val options = arrayOf("DAILY", "WEEKLY", "BI-WEEKLY", "MONTHLY", "YEARLY", "SEASONALLY")
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, options)
        spinner.setAdapter(adapter)

        // Handle the "Done" button
        val doneButton = dialogView.findViewById<TextView>(R.id.target_add_button)
        doneButton.setOnClickListener {
            // Retrieve the text entered in the TextViews
            val retrievedTexts = textViews.map { it.text.toString() }

            // Get the selected option from the Spinner
            selectedOption = spinner.text.toString()
            onTextsRetrieved(retrievedTexts, selectedOption)

            // Dismiss the dialog
            alertDialog.dismiss()
        }

        dialogView.findViewById<ImageView>(R.id.cancel_dialog).setOnClickListener {
            alertDialog.dismiss()
            switch.isChecked = false // Turn off the switch if the dialog is cancelled
        }
        alertDialog.setOnCancelListener {
            switch.isChecked = false // Turn off the switch if the dialog is cancelled
        }

        alertDialog.show()
    }


    private fun setupSwitchListener(
        switch: SwitchCompat,
        dialogLayoutId: Int,
        textViewIds: List<Int>,
        onTextsRetrieved: (List<String>, String) -> Unit
    ) {
        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showDialog(switch, dialogLayoutId, textViewIds) { retrievedTexts, selectedOption ->
                    // Handle the texts and selected option here
                    onTextsRetrieved(retrievedTexts, selectedOption)
                }
            }
        }
    }
}