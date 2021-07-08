package com.atech.android.feature.test

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.atech.android.R
import com.atech.android.base.BaseFragment
import com.atech.android.databinding.FragmentTestMainBinding

class TestMainFragment : BaseFragment<FragmentTestMainBinding, TestMainViewModel>() {
    private val TAG = "TestMainFragment"
    override val viewModel: TestMainViewModel by viewModels()
    override val binding: FragmentTestMainBinding by lazy {
        FragmentTestMainBinding.inflate(layoutInflater)
    }

    override fun onInitViews() {
        super.onInitViews()
        binding.bottomAppBar.itemIconTintList = null
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragmentTestMain) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomAppBar, navHostFragment.navController)
        binding.bottomAppBar.setOnNavigationItemReselectedListener {

        }

        binding.btnMenuHome.setOnClickListener {
            if(binding.bottomAppBar.selectedItemId != R.id.testHomeFragment)
                binding.bottomAppBar.selectedItemId = R.id.testHomeFragment
        }

    }
}