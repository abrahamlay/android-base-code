package com.atech.android.feature.test.home

import androidx.fragment.app.viewModels
import com.atech.android.base.BaseFragment
import com.atech.android.databinding.FragmentTestNotificationBinding

class TestNotificationFragment : BaseFragment<FragmentTestNotificationBinding, TestNotificationViewModel>() {
    private val TAG = "TestMainFragment"
    override val viewModel: TestNotificationViewModel by viewModels()
    override val binding: FragmentTestNotificationBinding by lazy {
        FragmentTestNotificationBinding.inflate(layoutInflater)
    }

    override fun onInitViews() {
        super.onInitViews()


    }
}