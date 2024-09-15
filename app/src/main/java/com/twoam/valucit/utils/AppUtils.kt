package com.twoam.valucit.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

object AppUtils {

    fun Fragment.showToast(text: String){
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

}