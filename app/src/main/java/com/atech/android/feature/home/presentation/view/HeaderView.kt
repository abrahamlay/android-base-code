package com.atech.android.feature.home.presentation.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.atech.android.R
import com.atech.android.base.config.Constants
import com.atech.android.databinding.ViewHeaderBinding
import com.atech.android.domain.entities.MovieModel
import com.atech.android.domain.subscriber.ResultState
import com.atech.feature_home.presentation.adapter.MovieAdapter
import com.atech.android.feature.home.presentation.fragment.DetailFragment
import com.atech.android.feature.home.presentation.viewmodel.HomeViewModel


/**
 * Created by Abraham Lay on 12/06/20.
 */
class HeaderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr),
    MovieAdapter.OnClickListener {

    private var type: Int = 0

    private var binding: ViewHeaderBinding

    var homeViewModel: HomeViewModel? = null
    lateinit var lifecycle: LifecycleOwner
    private val adapter: MovieAdapter by lazy { MovieAdapter() }

    var doneLoadingListener: OnDoneLoading? = null

    init {
        adapter.onClickListener = this
        adapter.isHeader = true
        val array: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.HeaderView
        )

        val type = array.getInt(R.styleable.HeaderView_hvModule, Constants.POPULAR)
        val label = array.getString(R.styleable.HeaderView_hvLabel)
        array.recycle()
        binding = ViewHeaderBinding.inflate(LayoutInflater.from(context), this, true)

        binding.tvLabelSection.text = label
        this.type = type

        Log.d(TAG, "Type: $type")
        binding.errorView.btnRetry.setOnClickListener {
            fetchData(lifecycle)
        }

        setAdapter()

    }

    fun fetchData(lifecycleOwner: LifecycleOwner) {
        lifecycle = lifecycleOwner
        showLoading()
        hideError()
        binding.rvMovieList.visibility = View.VISIBLE
        when (type) {
            Constants.POPULAR -> {
                Log.d(TAG, "fetched Popular")
                homeViewModel?.fetchPopularMovie()
                homeViewModel?.popularMovies?.observe(lifecycle, Observer {
                    setView(it)
                })
            }
            Constants.TOP_RATED -> {
                Log.d(TAG, "fetched Top Rated")
                homeViewModel?.fetchTopRatedMovie()
                homeViewModel?.topRatedMovies?.observe(lifecycle, Observer {
                    setView(it)
                })
            }
            Constants.NOW_PLAYING -> {
                Log.d(TAG, "fetched Now Playing")
                homeViewModel?.fetchNowPlayingMovie()
                homeViewModel?.nowPlayingMovies?.observe(lifecycle, Observer {
                    setView(it)
                })
            }
            Constants.FAVORITE -> {
                Log.d(TAG, "fetched Favorite")
                homeViewModel?.fetchFavoriteMovies()
                homeViewModel?.favoriteMovies?.observe(lifecycle, Observer {
                    setView(it)
                })
            }
        }
    }

    private fun setView(resultState: ResultState<List<MovieModel>?>?) {
        hideLoading()
        when (resultState) {
            is ResultState.Success -> {
                Log.d(TAG, "data ${resultState.data}")
                setData(resultState.data)
            }
            is ResultState.Error -> {
                showError(resultState.throwable)
            }
            is ResultState.Loading -> {
                setData(resultState.data)
            }
        }
    }

    private fun setData(data: List<MovieModel>?) {
        if (data.isNullOrEmpty()) {
            visibility = GONE
            return
        }
        adapter.data = data
        adapter.notifyDataSetChanged()
        binding.rvMovieList.visibility = View.VISIBLE
    }

    private fun setAdapter() = with(binding) {
        rvMovieList.adapter = adapter
        val helper: SnapHelper = PagerSnapHelper()
        val linearLayoutManager = LinearLayoutManager(context)
        helper.attachToRecyclerView(rvMovieList)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rvMovieList.layoutManager = linearLayoutManager
    }

    private fun showError(throwable: Throwable) = with(binding) {
        rvMovieList.visibility = View.GONE
        errorView.root.visibility = View.VISIBLE
        errorView.tvErrorMessage.text = throwable.localizedMessage
        Log.d("MainFragmentSV", "showError + ${throwable.localizedMessage}")
    }

    private fun hideError() = with(binding) {
        errorView.root.visibility = View.GONE
    }

    private fun showLoading() = with(binding) {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() = with(binding) {
        doneLoadingListener?.doneLoading()
        progressBar.visibility = View.GONE
    }

    override fun onItemClicked(data: Any) {
        Toast.makeText(context, (data as MovieModel).title, Toast.LENGTH_SHORT).show()
        val bundle = bundleOf(Pair(DetailFragment.PARAM_DETAIL_MOVIE, data))
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }

    interface OnDoneLoading {
        fun doneLoading()
    }

    companion object {
        private const val TAG = "HeaderView"
    }
}