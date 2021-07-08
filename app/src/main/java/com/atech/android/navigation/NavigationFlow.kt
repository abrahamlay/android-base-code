package com.atech.android.navigation

sealed class NavigationFlow {
    class HomeFlow(val title: String) : NavigationFlow()
    class ProfileFlow(val title: String) : NavigationFlow()
}