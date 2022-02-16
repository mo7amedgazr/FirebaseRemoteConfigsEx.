package com.app.firebaseconfigs.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.firebaseconfigs.data.entity.AppConfigs
import com.app.firebaseconfigs.databinding.FragmentMaintenanceBinding
import com.app.firebaseconfigs.utils.AppUtils
import com.app.firebaseconfigs.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MaintenanceFragment : Fragment() {

    private var _binding: FragmentMaintenanceBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val mViewModel: MaintenanceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMaintenanceBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.appConfigsLiveData.observe(viewLifecycleOwner) {
            onAppConfigsReceived(it)
        }
    }

    private fun onAppConfigsReceived(appConfigs: AppConfigs) {
        if (appConfigs.requiresUpdate) {
            binding.clBanner.visible()
            binding.message = AppUtils.getMaintenanceMessage(appConfigs)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}