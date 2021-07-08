package com.atech.android.feature.test.home

import android.util.Log
import androidx.fragment.app.viewModels
import com.atech.android.base.BaseFragment
import com.atech.android.databinding.FragmentTestProfileBinding

class TestProfileFragment : BaseFragment<FragmentTestProfileBinding, TestProfileViewModel>() {
    private val TAG = "TestMainFragment"
    override val viewModel: TestProfileViewModel by viewModels()
    override val binding: FragmentTestProfileBinding by lazy {
        FragmentTestProfileBinding.inflate(layoutInflater)
    }

    override fun onInitViews() {
        super.onInitViews()
    }
}