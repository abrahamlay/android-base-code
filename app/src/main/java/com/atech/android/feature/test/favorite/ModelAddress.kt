package com.atech.android.feature.test.favorite

data class ModelAddress(
    val address: List<Addres>
)

data class Addres(
    val `data`: List<Data>,
    val name: String
)

data class Data(
    val `data`: List<DataX>,
    val name: String
)

data class DataX(
    val name: String
)

