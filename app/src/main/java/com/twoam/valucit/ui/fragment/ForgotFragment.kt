package com.twoam.valucit.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.twoam.valucit.R
import com.twoam.valucit.databinding.FragmentForgotBinding
import com.twoam.valucit.databinding.FragmentProfileBinding


class ForgotFragment : Fragment() {

    private lateinit var binding: FragmentForgotBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgot, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createAccount.setOnClickListener {
            findNavController().navigate(R.id.action_forgotFragment_to_registerFragment)
        }

        binding.login.setOnClickListener {
            findNavController().navigate(R.id.action_forgotFragment_to_loginFragment)
        }
    }
}