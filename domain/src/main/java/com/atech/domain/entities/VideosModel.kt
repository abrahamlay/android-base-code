package com.atech.domain.entities


import com.atech.domain.entities.VideoModel
import com.google.gson.annotations.SerializedName

data class VideosModel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("results")
    val results: List<VideoModel>?
)