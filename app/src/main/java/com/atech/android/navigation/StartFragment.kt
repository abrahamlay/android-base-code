package com.atech.android.navigation

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.atech.android.R
import com.atech.android.databinding.FragmentStartBinding
import com.atech.android.base.BaseFragment
import com.atech.android.base.viewmodel.BaseViewModel

class StartFragment : BaseFragment<FragmentStartBinding, BaseViewModel>() {

    override val viewModel: BaseViewModel by viewModels()
    override val binding: FragmentStartBinding by lazy {
        FragmentStartBinding.inflate(layoutInflater)
    }

    override fun onInitViews() {
        super.onInitViews()
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragmentStart) as NavHostFragment
        NavigationUI.setupWithNavController(binding.navView, navHostFragment.navController)
        binding.navView.setOnNavigationItemReselectedListener {
            when (it.itemId){
                R.id.homeBaseFragment ->{

                }
            }
        }
//        binding.navView.setOnNavigationItemSelectedListener {
//            when (it.itemId){
//                R.id.homeBaseFragment ->{
//                    onBackPress()
//                }
//                R.id.profileFragment ->{
//                    findNavController().navigate()
//                }
//            }
//            true
//        }
    }

//    override fun onBackPress() {
//        findNavController().navigateUp()
//    }
}