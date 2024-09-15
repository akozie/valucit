package com.twoam.valucit.utils

import android.R
import android.content.Context
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat

class SwitchColorState(context: Context) {


    // Create a ColorStateList for the thumb (the small circle)
    val thumbStates = ColorStateList(
        arrayOf(
            intArrayOf(R.attr.state_checked), // Checked state
            intArrayOf(-R.attr.state_checked) // Unchecked state
        ),
        intArrayOf(
            ContextCompat.getColor(context, com.twoam.valucit.R.color.yellow), // Color when checked
            ContextCompat.getColor(context, R.color.darker_gray) // Color when unchecked
        )
    )

    // Create a ColorStateList for the track (the background)
    val trackStates = ColorStateList(
        arrayOf(
            intArrayOf(R.attr.state_checked), // Checked state
            intArrayOf(-R.attr.state_checked) // Unchecked state
        ),
        intArrayOf(
            ContextCompat.getColor(context, com.twoam.valucit.R.color.yellow), // Color when checked
            ContextCompat.getColor(context, R.color.white) // Color when unchecked
        )
    )
}