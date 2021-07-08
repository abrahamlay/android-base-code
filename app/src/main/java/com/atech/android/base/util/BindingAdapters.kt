package com.atech.android.base.util

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.EpoxyModel
import com.atech.android.R
import com.atech.android.base.config.Constants
import com.atech.android.feature.test.utils.CirclePagerIndicatorDecoration

object BindingAdapters {
    @BindingAdapter("imageUrl")
    @JvmStatic fun ImageView.bindImageUrl(imageUrl:String) {
        imageUrl?.let {
            val addedUrl =
                if (!imageUrl.contains("https://") || !imageUrl.contains("http://")) String.format(
                    Constants.MOVIE_THUMBNAIL_BASE_URL_LARGE,
                    imageUrl
                ) else imageUrl
            GlideHelper.showThumbnail(addedUrl, this, context)
        }
    }

    @BindingAdapter("imageViewUrl")
    @JvmStatic fun ImageView.bindImageViewUrl(imageViewUrl:String) {
        imageViewUrl?.let {
            GlideHelper.showThumbnail(it, this, context)
        }
    }

    @BindingAdapter("imageViewResource")
    @JvmStatic fun ImageView.bindImageViewResource(imageViewResource:Int) {
        this.setImageResource(imageViewResource)
    }

    @BindingAdapter("selected")
    @JvmStatic fun LinearLayoutCompat.bindSelected(selected:Boolean) {
        this.isSelected = selected
//        if(!selected){
//            this.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
//        }else{
//            this.setBackgroundColor(ContextCompat.getColor(context, R.color.city_selected))
//        }
    }

    @BindingAdapter("textViewSelected")
    @JvmStatic fun TextView.bindTextViewSelected(textViewSelected:Boolean) {
        this.isSelected = textViewSelected
    }

    @BindingAdapter("imageViewSelected")
    @JvmStatic fun ImageView.bindImageViewSelected(imageViewSelected:Boolean) {
        this.isSelected = imageViewSelected
    }

    @BindingAdapter(value = [ "carouselModel", "paddingLeft", "paddingTop", "paddingBottom", "paddingRight"], requireAll = false)
    @JvmStatic fun Carousel.bindModel(models:List<EpoxyModel<*>>,
                                      paddingLeft:Int=0,
                                      paddingTop:Int=0,
                                      paddingBottom:Int=0,
                                      paddingRight:Int=0, ) {
        this.setModels(models)
        this.addItemDecoration(CirclePagerIndicatorDecoration())
        this.setPadding(Carousel.Padding.dp(
            paddingLeft,
            paddingTop,
            paddingRight,
            paddingBottom,
            0
        ))

        val snapHelper = PagerSnapHelper()
        this.onFlingListener = null
        snapHelper.attachToRecyclerView(this)
    }
}