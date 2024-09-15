package com.twoam.valucit.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.twoam.valucit.R
import com.twoam.valucit.databinding.FragmentProfileBinding
import com.twoam.valucit.ui.activity.DashboardActivity
import com.twoam.valucit.ui.activity.MainActivity
import com.twoam.valucit.utils.SwitchColorState


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var switchState: SwitchColorState

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        switchState = SwitchColorState(requireContext())

        binding.twoFaBtn.thumbTintList = switchState.thumbStates
        binding.twoFaBtn.trackTintList = switchState.trackStates


        binding.editProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }
        binding.viewAccounts.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_accountFragment)
        }
        binding.viewCategories.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_categoriesFragment)
        }
        binding.connectedBanks.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_connectedBanksFragment)
        }
        binding.changePassword.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_changePasswordFragment)
        }
        binding.logOut.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
    }
}