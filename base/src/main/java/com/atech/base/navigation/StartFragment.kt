package com.atech.base.navigation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.atech.base.BaseFragment
import com.atech.base.databinding.FragmentStartBinding
import com.atech.base.viewmodel.BaseViewModel
import com.atech.navigation.NavigationFlow
import com.atech.navigation.ToFlowNavigatable

class StartFragment : BaseFragment<FragmentStartBinding, BaseViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // decide where to go on the first app launch, check auth tokens if login needed etc...
        setSelectedTab(0)
    }

    private fun setSelectedTab(selectedTab: Int) {
        when (selectedTab) {
            0 -> (requireActivity() as ToFlowNavigatable).navigateToFlow(NavigationFlow.HomeFlow("This Home Page"))
            1 -> (requireActivity() as ToFlowNavigatable).navigateToFlow(NavigationFlow.ProfileFlow("This Profile Page"))
        }
    }

    override val viewModel: BaseViewModel by viewModels()
    override val binding: FragmentStartBinding by lazy {
        FragmentStartBinding.inflate(layoutInflater)
    }
}