package com.atech.android.navigation

import androidx.navigation.NavController
import com.atech.android.NavGraphMainDirections

class Navigator {
    lateinit var navController: NavController

    // navigate on main thread or nav component crashes sometimes
    fun navigateToFlow(navigationFlow: NavigationFlow) = when (navigationFlow) {
        is NavigationFlow.HomeFlow -> navController.navigate(NavGraphMainDirections.actionGlobalNavGraphHome(navigationFlow.title))
        is NavigationFlow.ProfileFlow -> navController.navigate(NavGraphMainDirections.actionGlobalNavGraphProfile(navigationFlow.title))
    }
}