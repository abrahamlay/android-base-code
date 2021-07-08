package com.atech.android.feature.test.favorite

interface FavoriteListener {
    fun onStreetClick(index : Int)
    fun onDistrictClick(index : Int)
    fun onCityClick(index : Int)
}