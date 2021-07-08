package com.atech.android.feature.test.home

import com.airbnb.epoxy.EpoxyModel

data class ModelHomeSubMenu(
    val image:Int,
    val title:String
    )

data class ModelHomeSubMenu2(
    val image:Int,
    val title:String
)

data class ModelHomeCarouselBanner(
    val image:String,
    val title:String
)

data class ModelHomeRecomendation(
    val image:String,
    val label:String,
    val title:String,
    val companyLogo: String,
    val companyName:String
)

data class ModelHomeFixesAndRepair(
    val image:String,
    val title:String
)

data class ModelHomeCarousel(
    val image1:String,
    val title1:String,
    val image2:String,
    val title2:String,
    val image3:String,
    val title3:String,
)

data class ModelHomeCarouselContainer(val models: List<EpoxyModel<*>>)
