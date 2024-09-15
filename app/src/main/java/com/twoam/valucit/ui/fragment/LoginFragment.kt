package com.twoam.valucit.ui.fragment

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.switchmaterial.SwitchMaterial
import com.twoam.valucit.R
import com.twoam.valucit.databinding.FragmentLoginBinding
import com.twoam.valucit.ui.activity.DashboardActivity
import com.twoam.valucit.utils.AppUtils.showToast
import com.twoam.valucit.utils.SwitchColorState
import java.util.concurrent.Executor


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var switchState: SwitchColorState
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBiometricLogin()
        switchState = SwitchColorState(requireContext())

        binding.switchBtn.thumbTintList = switchState.thumbStates
        binding.switchBtn.trackTintList = switchState.trackStates


        binding.login.setOnClickListener {
            startActivity(Intent(requireContext(), DashboardActivity::class.java))
        }

        binding.forgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotFragment)
        }

        binding.createAccount.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        binding.biometrics.setOnClickListener {
            if (isBiometricSupported()) {
                showBiometricPrompt()
            } else {
                // Fallback to password or other authentication methods
                showToast("Biometric authentication not supported")
            }
        }
    }

    private fun isBiometricSupported(): Boolean {
        val biometricManager = BiometricManager.from(requireContext())
        return when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS -> true
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                false // No biometric hardware
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                false // Biometric hardware is unavailable
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                false // User has not enrolled any biometrics
            else -> false
        }
    }

    // Initialize biometric login
    private fun setupBiometricLogin() {
        // Executor to run the biometric prompt
        executor = ContextCompat.getMainExecutor(requireContext())

        // Biometric prompt callback to handle authentication result
        biometricPrompt = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                // Handle error (e.g., show error message)
                showToast("Authentication error: $errString")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Log.d("BIOMETRICS", result.authenticationType.toString()) //2
                Log.d("BIOMETRICSSSS", result.cryptoObject.toString()) //null
                // Handle success (e.g., navigate to next screen)
                showToast("Authentication succeeded!")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                // Handle failure (e.g., show error message)
                showToast("Authentication failed")
            }
        })

        // Info to display in the biometric dialog
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Login")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use App Password")
            .build()
    }


    private fun showBiometricPrompt() {
        biometricPrompt.authenticate(promptInfo)
    }
}