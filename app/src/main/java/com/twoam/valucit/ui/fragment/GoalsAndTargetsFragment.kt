package com.twoam.valucit.ui.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.twoam.valucit.R
import com.twoam.valucit.adapter.GenericRecyclerAdapter
import com.twoam.valucit.adapter.GenericRecyclerBindingInterface
import com.twoam.valucit.databinding.FragmentGoalsAndTargetBinding
import com.twoam.valucit.model.dashboard.GoalsAndTargetModel
import java.util.*


class GoalsAndTargetsFragment : Fragment() {

    private lateinit var binding: FragmentGoalsAndTargetBinding
    private lateinit var goalsAndTargetList: ArrayList<GoalsAndTargetModel>
    private lateinit var selectedCategory: String
    private lateinit var selectedPriority: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_goals_and_target, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goalsAndTargetList()
        goalsAndTargetSetUp()

        binding.continueBtn.setOnClickListener {
            findNavController().navigate(R.id.action_goalsAndTargetsFragment_to_investmentsFragment)
        }

        // List of TextView IDs that are common across all dialogs
        val commonTextViewIds = listOf(R.id.product_name, R.id.amount)


        binding.addGoals.setOnClickListener {
            findNavController().navigate(R.id.action_goalsAndTargetsFragment_to_goalsBottomDialogFragment2)
        }

    }



    private fun goalsAndTargetSetUp() {
        val bindingInterface = object : GenericRecyclerBindingInterface<GoalsAndTargetModel> {
            override fun bindData(item: GoalsAndTargetModel, view: View) {
                val img: ImageView = view.findViewById(R.id.target_img)
                val amount: TextView = view.findViewById(R.id.amount)
                val name: TextView = view.findViewById(R.id.target_name)
                val status: TextView = view.findViewById(R.id.status)
                val date: TextView = view.findViewById(R.id.calendar)
                img.setBackgroundResource(item.image)
                amount.text = item.target_amount
                status.text = item.target_status
                date.text = item.target_date
                name.text = item.target_name
            }
        }

        val adapter = GenericRecyclerAdapter(goalsAndTargetList, R.layout.goals_and_targets_item_layout, bindingInterface, null)
        binding.goalsAndTargetRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.goalsAndTargetRecyclerView.adapter = adapter
    }

    private fun goalsAndTargetList() {
        goalsAndTargetList = arrayListOf()
        goalsAndTargetList.add(GoalsAndTargetModel(R.drawable.beach_access, "SUMMER VACATION", "LOW", "2,400", "12-JUL-25"))
        goalsAndTargetList.add(GoalsAndTargetModel(R.drawable.monetization_on, "CASH SAVINGS", "HIGH", "12,400", "12-DEC-25"))
        goalsAndTargetList.add(GoalsAndTargetModel(R.drawable.library_books, "EDUCATION", "HIGH", "17,400", "4-SEP-27"))
        goalsAndTargetList.add(GoalsAndTargetModel(R.drawable.directions_car, "VEHICLE", "MOD", "20,000", "12-NOV-25"))
        goalsAndTargetList.add(GoalsAndTargetModel(R.drawable.medical_services, "HEALTHCARE", "MOD", "1,200", "30-DEC-25"))

 }

    private fun showDialog(
        dialogLayoutId: Int,
        textViewIds: List<Int>,
        onTextsRetrieved: (List<String>, List<String>, String) -> Unit
    ) {
        val dialogView = LayoutInflater.from(requireContext()).inflate(dialogLayoutId, null)
        val alertDialog = AlertDialog.Builder(requireContext()).setView(dialogView).create()

        // Initialize the TextViews
        val textViews = textViewIds.map { dialogView.findViewById<TextView>(it) }


        // Initialize the Category Spinner
        val category = dialogView.findViewById<AutoCompleteTextView>(R.id.choose_category)
        val categoryOptions = arrayOf("DAILY", "WEEKLY", "BI-WEEKLY", "MONTHLY", "YEARLY", "SEASONALLY")
        val categoryAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, categoryOptions)
        category.setAdapter(categoryAdapter)

        // Initialize the priority Spinner
        val priority = dialogView.findViewById<AutoCompleteTextView>(R.id.priority)
        val priorityOptions = arrayOf("HIGH", "MEDIUM", "LOW")
        val priorityAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, priorityOptions)
        priority.setAdapter(priorityAdapter)

        // Initialize the Date Picker TextView
        val dateTextView = dialogView.findViewById<TextView>(R.id.due_date)
        var selectedDate = ""

        // Show DatePickerDialog when the dateTextView is clicked
        dateTextView.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireContext(), R.style.BlackDatePickerDialog, { _, selectedYear, selectedMonth, selectedDay ->
                selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                dateTextView.text = selectedDate // Display selected date in the TextView
            }, year, month, day)

            datePickerDialog.show()
        }

        // Handle the "Done" button
        val doneButton = dialogView.findViewById<TextView>(R.id.add_goal_button)
        doneButton.setOnClickListener {
            // Retrieve the text entered in the TextViews
            val retrievedTexts = textViews.map { it.text.toString() }

            // Get the selected option from the Spinner
            selectedCategory = category.text.toString()
            selectedPriority = priority.text.toString()

            // Pass the retrieved data back to the calling function
            onTextsRetrieved(retrievedTexts, listOf(selectedCategory, selectedPriority), selectedDate)

            // Dismiss the dialog
            alertDialog.dismiss()
        }

        dialogView.findViewById<ImageView>(R.id.cancel_dialog).setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }
}