package com.atech.android.feature.profile.presentation

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.atech.android.databinding.FragmentProfileBinding
import com.atech.android.base.BaseFragment
import com.atech.android.base.viewmodel.BaseViewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding, BaseViewModel>() {
    override val viewModel: BaseViewModel by viewModels()
    override val binding: FragmentProfileBinding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun onInitViews() {
        super.onInitViews()
    }
}