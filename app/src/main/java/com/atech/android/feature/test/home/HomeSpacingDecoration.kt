package com.atech.android.feature.test.home

import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class HomeSpacingDecoration : ItemDecoration() {
    private val TAG = "HomeSpacingDecoration"
    private var outerPadding = -1
    private var innerPadding = -1
    private var padding2dp = -1
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (outerPadding == -1 || innerPadding == -1) {
            val m: DisplayMetrics = view.resources.displayMetrics
            outerPadding = TypedValue.applyDimension(COMPLEX_UNIT_DIP, OUTER_PADDING_DP, m).toInt()
            innerPadding = TypedValue.applyDimension(COMPLEX_UNIT_DIP, INNER_PADDING_DP, m).toInt()
            padding2dp = TypedValue.applyDimension(COMPLEX_UNIT_DIP, PADDING_2DP, m).toInt()
        }
        val position = parent.getChildAdapterPosition(view)
        val layoutManager = parent.layoutManager as GridLayoutManager?
        val spanSizeLookup = layoutManager!!.spanSizeLookup

        // Zero everything out for the common case
        outRect.setEmpty()
        val spanSize = spanSizeLookup.getSpanSize(position)
        val spanCount = layoutManager.spanCount
        val spanIndex = spanSizeLookup.getSpanIndex(position, spanCount)
//        Log.d(TAG, "$spanSize      $spanCount    $spanIndex")
        when {
            spanSize == 4 ->{
                if(spanIndex ==0){
                    outRect.left = outerPadding
                }else if(spanIndex ==8){
                    outRect.right = outerPadding
                }else{
                    outRect.left = innerPadding
                    outRect.right = innerPadding
                }
                outRect.top = innerPadding
            }
            spanSize == 3 ->{
                if(spanIndex ==0){
                    outRect.left = outerPadding
//                    outRect.right = padding2dp
                }else if(spanIndex ==10){
//                    outRect.left = padding2dp
                    outRect.right = outerPadding
                }else{
                    outRect.left = padding2dp
                    outRect.right = padding2dp
                }
                outRect.top = innerPadding
            }
            else -> {

            }
        }
    }

    companion object {
        private const val OUTER_PADDING_DP = 8F
        private const val INNER_PADDING_DP = 4F
        private const val PADDING_2DP = 2F
    }
}