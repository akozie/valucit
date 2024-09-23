package com.twoam.valucit.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.twoam.valucit.R
import com.twoam.valucit.adapter.GenericRecyclerAdapter
import com.twoam.valucit.adapter.GenericRecyclerBindingInterface
import com.twoam.valucit.databinding.FragmentCategoriesBinding
import com.twoam.valucit.model.dashboard.CategoryModel

/**
 * A simple [Fragment] subclass.
 * Use the [CategoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var categoryList: ArrayList<CategoryModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        categoryList()
        categorySetUp()
    }

    private fun categorySetUp() {
        val bindingInterface =
            object : GenericRecyclerBindingInterface<CategoryModel> {
                override fun bindData(
                    item: CategoryModel,
                    view: View,
                ) {
                    val categoryName: TextView = view.findViewById(R.id.item_name)
                    val categoryType: TextView = view.findViewById(R.id.category_type)
                    categoryName.text = item.category_name
                    categoryType.text = item.country_type
                }
            }

        val adapter = GenericRecyclerAdapter(categoryList, R.layout.categories_item_layout, bindingInterface, null)
        binding.categoriesRecycleview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.categoriesRecycleview.adapter = adapter
    }

    private fun categoryList() {
        categoryList = arrayListOf()
        categoryList.add(CategoryModel("GROCERIES", "OUTFLOW"))
        categoryList.add(CategoryModel("TRANSPORT", "OUTFLOW"))
        categoryList.add(CategoryModel("ENTERTAINMENT", "OUTFLOW"))
        categoryList.add(CategoryModel("FOOD", "OUTFLOW"))
        categoryList.add(CategoryModel("INCOME", "INFLOW"))
    }
}
