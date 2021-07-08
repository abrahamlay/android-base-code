package com.atech.android.feature.home.presentation.fragment

import android.graphics.drawable.Drawable
import android.view.*
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atech.android.R
import com.atech.android.base.BaseActivity
import com.atech.android.base.config.Constants
import com.atech.android.databinding.FragmentDetailBinding
import com.atech.android.base.BaseFragment
import com.atech.domain.entities.DetailMovieModel
import com.atech.domain.entities.MovieModel
import com.atech.domain.entities.ReviewModel
import com.atech.domain.entities.VideoModel
import com.atech.domain.subscriber.ResultState
import com.atech.android.feature.home.presentation.adapter.ReviewAdapter
import com.atech.android.feature.home.presentation.viewmodel.DetailViewModel
import com.atech.android.base.util.DateFormater
import com.atech.android.base.util.GlideHelper
import com.atech.android.navigation.DeepLinkDestination
import com.atech.android.navigation.StartFragment
import com.atech.android.navigation.deepLinkNavigateTo
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped


@FragmentScoped
@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    @FragmentScoped
    override val viewModel: DetailViewModel by viewModels()
    override val binding: FragmentDetailBinding by lazy {
        // create ContextThemeWrapper from the original Activity Context with the custom theme
        val contextThemeWrapper = ContextThemeWrapper(activity, R.style.Theme_AtechAndroidStarter_NoActionBar)

        // clone the inflater using the ContextThemeWrapper
        val localInflater = layoutInflater.cloneInContext(contextThemeWrapper)
        FragmentDetailBinding.inflate(localInflater)
    }
    private val args: DetailFragmentArgs by navArgs()

    private var menu: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var adapter: ReviewAdapter
    private val detailMovie: MovieModel? by lazy { arguments?.getParcelable(PARAM_DETAIL_MOVIE) }

    companion object {
        const val PARAM_DETAIL_MOVIE = "detailMovie"
    }

    override fun onInitViews() {
        super.onInitViews()
        setHasOptionsMenu(true)
        initToolbar()
        if (detailMovie != null) {
            initDetail()
        }
    }

    private fun initDetail() = with(binding) {
        detailOverview.text = detailMovie?.overview
        detailRating.text = detailMovie?.popularity.toString()
        val releaseDate = DateFormater.changeDateFormat(
            detailMovie?.releaseDate
        )
        detailDateRelease.text = releaseDate
        val url =
            String.format(
                Constants.MOVIE_THUMBNAIL_BASE_URL_EXTRA_LARGE,
                detailMovie?.backdropPath
            )
        GlideHelper.showBackDrop(url, ivMovie, requireContext())
        getDetailData()
    }

    override fun onInitObservers() {
        super.onInitObservers()
        binding.reviewView.errorView.btnRetry.setOnClickListener {
            getDetailData()
        }
        viewModel.getVideosLiveData.observe(viewLifecycleOwner, Observer {
            initVideo(it)
        })
        viewModel.getReviewLiveData.observe(viewLifecycleOwner, Observer {
            onResultLoaded(it)
        })
        viewModel.getFavoriteMovieLiveData.observe(this, Observer {
            checkingMovieFavoriteStatus(it)
        })
        viewModel.insertMovieLiveData.observe(this, Observer {
            afterInsertFavoriteMovie(it)
        })
        viewModel.deleteMovieLiveData.observe(this, Observer {
            afterDeleteFavoriteMovie(it)
        })
        viewModel.getDetailMovieLiveData.observe(this, Observer {
            setDetail(it)
        })
    }

    private fun setDetail(it: ResultState<DetailMovieModel>?) {
        when (it) {
            is ResultState.Success -> {
                setDetailData(it.data)
            }
            is ResultState.Error -> {
                Toast.makeText(context, it.throwable.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setDetailData(data: DetailMovieModel?) = with(binding) {
        detailOverview.text = data?.overview
        detailRating.text = data?.popularity.toString()
        val releaseDate = DateFormater.changeDateFormat(
            data?.releaseDate
        )
        detailDateRelease.text = releaseDate
        val url =
            String.format(
                Constants.MOVIE_THUMBNAIL_BASE_URL_EXTRA_LARGE,
                data?.backdropPath
            )
        GlideHelper.showBackDrop(url, ivMovie, requireContext())
    }

    private fun afterDeleteFavoriteMovie(it: ResultState<Int>?) {
        when (it) {
            is ResultState.Success -> {
                isFavorite = !isFavorite
                Toast.makeText(context, getString(R.string.favorite_deleted), Toast.LENGTH_SHORT)
                    .show()
            }
            is ResultState.Error -> {
                Toast.makeText(context, it.throwable.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
        setFavoriteIcon()
    }

    private fun afterInsertFavoriteMovie(it: ResultState<Long>?) {
        when (it) {
            is ResultState.Success -> {
                isFavorite = !isFavorite
                Toast.makeText(context, getString(R.string.favorite_added), Toast.LENGTH_SHORT)
                    .show()
            }
            is ResultState.Error -> {
                Toast.makeText(context, it.throwable.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
        setFavoriteIcon()
    }

    private fun checkingMovieFavoriteStatus(it: ResultState<MovieModel?>?) {
        it?.let {
            when (it) {
                is ResultState.Success -> {
                    isFavorite = true
                }
            }

            setFavoriteIcon()
        }
    }

    private fun setFavoriteIcon() {
        menu?.let {
            if(it.size() > 0){
                it.getItem(0)?.icon = getFavoriteIcon()
            }
        }
    }


    private fun onResultLoaded(resultState: ResultState<List<ReviewModel>>?) {
        hideLoading()
        when (resultState) {
            is ResultState.Success -> {
                initAdapter(resultState)
            }
            is ResultState.Error -> {
                showError(resultState.throwable)
            }
        }

    }

    private fun initAdapter(resultState: ResultState.Success<List<ReviewModel>>) = with(binding.reviewView){
        adapter = ReviewAdapter(resultState.data)
        rvReviewsList.layoutManager = getLayoutManager()
        rvReviewsList.adapter = adapter
        adapter.onClickListener = object :ReviewAdapter.OnClickListener{
            override fun onItemClicked(data: Any) {
                ((((parentFragment as NavHostFragment)
                    .parentFragment as HomeBaseFragment)
                    .parentFragment as NavHostFragment)
                    .parentFragment as StartFragment)
                    .findNavController().navigate(R.id.action_start_to_testMainFragment)
            }
        }
    }

    private fun initToolbar() = with(binding) {

        (activity as BaseActivity<*, *>).setSupportActionBar(toolbar)

        val supportActionBar = (activity as BaseActivity<*,*>).supportActionBar

        if (supportActionBar != null) {
            supportActionBar.title = detailMovie?.title
            supportActionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initVideo(state: ResultState<List<VideoModel>>) {
        if (state is ResultState.Success) {
            state.data.getOrNull(0)?.key?.let { initWebView(it) }
        }
    }

    private fun initWebView(key: String?) = with(binding) {
        //build your own src link with your video ID
        val videoStr =
            "<html><head><style>body{margin:0} iframe{margin:0;width:100%}</style></head><body><iframe style=\"margin: 0;\" width=\"100%\" height=\"240\" src=\"https://www.youtube.com/embed/$key\" autoplay=\"1\" allowfullscreen/></body></html>"

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }
        }
        val ws: WebSettings = webView.settings
        ws.javaScriptEnabled = true
        webView.loadData(videoStr, "text/html", "utf-8")
    }

    private fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    private fun showError(throwable: Throwable) = with(binding.reviewView.errorView) {
        root.visibility = View.VISIBLE
        tvErrorMessage.text = throwable.localizedMessage
    }

    private fun hideError()  = with(binding.reviewView.errorView){
        root.visibility = View.GONE
    }

    private fun getDetailData() {
        detailMovie?.id?.let {
            hideError()
            showLoading()
            viewModel.fetchReviews(it)
            viewModel.fetchVideos(it)
            viewModel.fetchFavoriteMovie(it)
            viewModel.fetchDetailMovie(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu
        activity?.menuInflater?.inflate(R.menu.detail_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setFavoriteMovie()
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteMovie() {
        detailMovie?.let {
            if (!isFavorite) {
                viewModel.insertFavoriteMovie(it)
            } else {
                viewModel.deleteFavoriteMovie(it)
            }
        }
    }

    private fun getFavoriteIcon(): Drawable? {
        return if (isFavorite) ContextCompat.getDrawable(
            requireContext(),
            R.drawable.ic_favorite_filled
        ) else ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_unfilled)
    }

    private fun showLoading() = with(binding.reviewView) {
        progressBarView.root.visibility = View.VISIBLE
    }

    private fun hideLoading() = with(binding.reviewView) {
        progressBarView.root.visibility = View.GONE
    }

}