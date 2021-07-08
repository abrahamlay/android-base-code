package com.atech.feature_home.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atech.android.R
import com.atech.android.base.config.Constants
import com.atech.android.databinding.ItemMovieHeaderBinding
import com.atech.android.databinding.ItemMovieSectionBinding
import com.atech.android.base.util.DateFormater
import com.atech.android.base.util.GlideHelper
import com.atech.domain.entities.MovieModel

/**
 * Created by Abraham Lay on 2019-10-13.
 */
class MovieAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, pos: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)

        return if (isHeader) MovieItemHeaderViewHolder(
            ItemMovieHeaderBinding.bind(
                inflater.inflate(
                    R.layout.item_movie_header,
                    viewGroup,
                    false
                )
            )
        )
        else
            MovieItemSectionViewHolder(
                ItemMovieSectionBinding.bind(
                    inflater.inflate(
                        R.layout.item_movie_section,
                        viewGroup,
                        false
                    )
                )
            )

    }

    var isHeader: Boolean = false

    var data: List<MovieModel>? = arrayListOf()

    var onClickListener: OnClickListener? = null

    override fun getItemCount(): Int {
        return data?.size!!
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, pos: Int) {
        when (viewHolder) {
            is MovieItemHeaderViewHolder -> {
                setItemHeaderData(viewHolder, pos)
            }
            is MovieItemSectionViewHolder -> {
                setItemHeaderData(viewHolder, pos)
            }
        }
    }

    private fun setItemHeaderData(viewHolder: MovieItemHeaderViewHolder, pos: Int) {
        viewHolder.bind(data!![pos])
    }

    private fun setItemHeaderData(viewHolder: MovieItemSectionViewHolder, pos: Int) {
        viewHolder.bind(data!![pos])
    }

    interface OnClickListener {
        fun onItemClicked(data: Any)
    }

    inner class MovieItemSectionViewHolder(private val itemMovieBinding: ItemMovieSectionBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {

        fun bind(movieModel: MovieModel) = with(itemMovieBinding) {
            this.movieModel = movieModel
            this.listener = onClickListener
            this.executePendingBindings()
        }
    }

    inner class MovieItemHeaderViewHolder(private val itemMovieBinding: ItemMovieHeaderBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {

        fun bind(movieModel: MovieModel) = with(itemMovieBinding) {
            this.movieModel = movieModel
            this.listener = onClickListener
            this.executePendingBindings()
        }
    }
}