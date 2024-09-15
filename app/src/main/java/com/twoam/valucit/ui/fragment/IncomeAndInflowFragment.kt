package com.twoam.valucit.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.twoam.valucit.R
import com.twoam.valucit.databinding.FragmentIncomeAndInflowBinding
import com.twoam.valucit.utils.SwitchColorState


/**
 * A simple [Fragment] subclass.
 * Use the [IncomeAndInflowFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IncomeAndInflowFragment : Fragment() {

    private lateinit var binding: FragmentIncomeAndInflowBinding
    private lateinit var switchState: SwitchColorState
    private lateinit var salaryAmountText: String
    private lateinit var pensionAmountText: String
    private lateinit var overtimeAmountText: String
    private lateinit var investmentAmountText: String
    private lateinit var giftsAmountText: String
    private lateinit var otherAmountText: String
    private lateinit var selectedOption: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_income_and_inflow, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.continueBtn.setOnClickListener {
            findNavController().navigate(R.id.action_incomeAndInflowFragment_to_expensesAndBillsFragment)
        }

        switchState = SwitchColorState(requireContext())

        binding.salary.thumbTintList = switchState.thumbStates
        binding.salary.trackTintList = switchState.trackStates

        binding.overtime.thumbTintList = switchState.thumbStates
        binding.overtime.trackTintList = switchState.trackStates

        binding.pension.thumbTintList = switchState.thumbStates
        binding.pension.trackTintList = switchState.trackStates

        binding.investment.thumbTintList = switchState.thumbStates
        binding.investment.trackTintList = switchState.trackStates

        binding.gifts.thumbTintList = switchState.thumbStates
        binding.gifts.trackTintList = switchState.trackStates

        binding.other.thumbTintList = switchState.thumbStates
        binding.other.trackTintList = switchState.trackStates


        // List of TextView IDs that are common across all dialogs
        val commonTextViewIds = listOf(R.id.amount)


        setupSwitchListener(
            binding.salary,
            R.layout.income_inflow_layout,
            commonTextViewIds
        ) { retrievedTexts, selectedOption ->
            salaryAmountText = "${retrievedTexts[0]} - $selectedOption"
            Log.d("SALARY_AMOUNT", salaryAmountText)
        }

        setupSwitchListener(
            binding.overtime,
            R.layout.income_inflow_layout,
            commonTextViewIds
        ) { retrievedTexts, selectedOption ->
            // Update the relevant TextViews based on the retrieved data
            overtimeAmountText = "${retrievedTexts[0]} - $selectedOption" // Update TextView1 with first retrieved text
            Log.d("SECOND_TEXT", overtimeAmountText)
        }
        setupSwitchListener(
            binding.pension,
            R.layout.income_inflow_layout,
            commonTextViewIds
        ) { retrievedTexts, selectedOption ->
            // Update the relevant TextViews based on the retrieved data
            pensionAmountText = retrievedTexts[0] // Update TextView1 with first retrieved text
            Log.d("SECOND_TEXT", pensionAmountText)
        }
        setupSwitchListener(
            binding.investment,
            R.layout.income_inflow_layout,
            commonTextViewIds
        ) { retrievedTexts, selectedOption ->
            // Update the relevant TextViews based on the retrieved data
            investmentAmountText = retrievedTexts[0] // Update TextView1 with first retrieved text
            Log.d("SECOND_TEXT", investmentAmountText)
        }
        setupSwitchListener(
            binding.gifts,
            R.layout.income_inflow_layout,
            commonTextViewIds
        ) { retrievedTexts, selectedOption ->
            // Update the relevant TextViews based on the retrieved data
            giftsAmountText = retrievedTexts[0] // Update TextView1 with first retrieved text
            Log.d("SECOND_TEXT", giftsAmountText)
        }
        setupSwitchListener(
            binding.other,
            R.layout.income_inflow_layout,
            commonTextViewIds
        ) { retrievedTexts, selectedOption ->
            // Update the relevant TextViews based on the retrieved data
            otherAmountText = retrievedTexts[0] // Update TextView1 with first retrieved text
            Log.d("SECOND_TEXT", otherAmountText)
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

            // Pass the retrieved data back to the calling function

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