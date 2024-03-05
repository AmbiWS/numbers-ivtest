package com.ambiws.numbers_ivtest.features.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.ambiws.numbers_ivtest.base.BaseFragment
import com.ambiws.numbers_ivtest.base.EmptyViewModel
import com.ambiws.numbers_ivtest.databinding.FragmentHomeBinding
import com.ambiws.numbers_ivtest.utils.logd

class HomeFragment : BaseFragment<EmptyViewModel, FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions: Map<String, Boolean> ->
            if (permissions.values.all { it }) {
                setupFragment()
            } else {
                Toast.makeText(requireContext(), "Missing important permissions", Toast.LENGTH_SHORT).show()
                requireActivity().finishAffinity()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Permissions, required for stable work
        runPermissionsFlow(onGranted = {
            setupFragment()
        })
    }

    private fun runPermissionsFlow(onGranted: () -> Unit) {
        // Implement permissions flow if necessary
        onGranted.invoke()
    }

    private fun setupFragment() {
        logd("Home fragment init.")
    }
}
