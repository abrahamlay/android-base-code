package com.atech.android.feature.home.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.atech.android.base.BaseFragment
import com.atech.android.base.viewmodel.BaseViewModel
import com.atech.android.databinding.FragmentHomeBaseBinding

class HomeBaseFragment : BaseFragment<FragmentHomeBaseBinding, BaseViewModel>() {
    private val TAG = "HomeBaseFragment"
    override val viewModel: BaseViewModel by viewModels()
    override val binding: FragmentHomeBaseBinding
        get() = FragmentHomeBaseBinding.inflate(layoutInflater)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        findNavController().currentDestination?.let {
            Log.d(TAG, "${it.id}")
            outState.putInt("fragmentId", it.id)
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            findNavController().navigate(it.getInt("fragmentId"))
        }
    }
}