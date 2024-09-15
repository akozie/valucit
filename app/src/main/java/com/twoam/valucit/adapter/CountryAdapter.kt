package com.twoam.valucit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.twoam.valucit.R
import com.twoam.valucit.model.dashboard.AllCurrency
import com.twoam.valucit.model.dashboard.Country

class CountryAdapter(context: Context, private val countryList: List<Country>) :
    ArrayAdapter<Country>(context, 0, countryList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.country_item_layout, parent, false)

        val country = getItem(position)
//        val flagImageView = view.findViewById<ImageView>(R.id.country_flag)
        val flagImageView = view.findViewById<TextView>(R.id.country_flag)
        val countryNameTextView = view.findViewById<TextView>(R.id.country_name)

        country?.let {
//            flagImageView.setImageResource(it.flagResId)
            countryNameTextView.text = it.name
            flagImageView.text = it.flagResId
        }

        return view
    }
}

