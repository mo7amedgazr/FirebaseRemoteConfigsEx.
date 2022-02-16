package com.app.firebaseconfigs.ui.splash

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.firebaseconfigs.R
import com.app.firebaseconfigs.data.entity.AppConfigs
import com.app.firebaseconfigs.databinding.FragmentSplashBinding
import com.app.firebaseconfigs.utils.AppUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val mViewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSplashBinding.inflate(inflater, container, false)
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
            GlobalScope.launch(Dispatchers.Main) {
                delay(3000)
                val bundle = bundleOf("message" to AppUtils.getMaintenanceMessage(appConfigs))
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}