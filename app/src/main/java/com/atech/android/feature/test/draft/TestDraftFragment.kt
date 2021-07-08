package com.atech.android.feature.test.home

import androidx.fragment.app.viewModels
import com.atech.android.base.BaseFragment
import com.atech.android.databinding.FragmentTestDraftBinding

class TestDraftFragment : BaseFragment<FragmentTestDraftBinding, TestDraftViewModel>() {
    private val TAG = "TestMainFragment"
    override val viewModel: TestDraftViewModel by viewModels()
    override val binding: FragmentTestDraftBinding by lazy {
        FragmentTestDraftBinding.inflate(layoutInflater)
    }

    override fun onInitViews() {
        super.onInitViews()


    }
}