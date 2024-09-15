package com.twoam.valucit.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.twoam.valucit.R
import com.twoam.valucit.databinding.FragmentGettingStartedBinding
import com.twoam.valucit.databinding.FragmentLoginBinding

class GettingStartedFragment : Fragment() {

    private lateinit var binding: FragmentGettingStartedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_getting_started, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.login.setOnClickListener {
            findNavController().navigate(GettingStartedFragmentDirections.actionGettingStartedFragmentToLoginFragment3())
        }
        binding.getValucit.setOnClickListener {
            findNavController().navigate(GettingStartedFragmentDirections.actionGettingStartedFragmentToRegisterFragment())
        }
    }
}