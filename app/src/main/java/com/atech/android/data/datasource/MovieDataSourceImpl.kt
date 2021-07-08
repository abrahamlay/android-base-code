package com.atech.android.data.datasource

import com.atech.android.base.util.SessionHelper
import com.atech.android.data.db.MovieDao
import com.atech.android.data.dtos.*
import com.atech.android.data.remote.MovieApi
import com.atech.android.domain.datasource.MovieDataSource
import com.atech.android.domain.entities.MovieModel
import io.reactivex.Flowable
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val api: MovieApi,
    private val movieDao: MovieDao,
    private val sessionHelper: SessionHelper
) : MovieDataSource {
    override fun getDiscoverMoviesByGenre(apiKey: String, map: HashMap<String, Any>): Flowable<MovieDto> =
        api.getDiscoverMoviesByGenre(apiKey, map)

    override fun getGenres(apiKey: String): Flowable<GenresDto> =
        api.getGenres(apiKey)

    override fun getReviews(movieId: Int, apiKey: String): Flowable<ReviewDto> =
        api.getReviews(movieId, apiKey)

    override fun getVideo(movieId: Int, apiKey: String): Flowable<VideoDto> =
        api.getVideo(movieId, apiKey)

    override fun getMovieDetails(movieId: Int, apiKey: String): Flowable<DetailMovieDto> =
        api.getMovieDetails(movieId, apiKey)

    override fun getPopularMovies(apiKey: String): Flowable<MovieDto> =
        api.getPopularMovies(apiKey)

    override fun getTopRatedMovies(apiKey: String): Flowable<MovieDto> =
        api.getTopRatedMovies(apiKey)

    override fun getNowPlayingMovies(apiKey: String): Flowable<MovieDto> =
        api.getNowPlayingMovies(apiKey)

    override fun getFavoriteMovies(): Flowable<List<MovieModel>?> =
        movieDao.selectFavoriteMovie()

    override fun getFavoriteMovie(movieId: Int): Flowable<MovieModel?> =
        movieDao.select(movieId)

    override fun insertFavoriteMovie(movieModel: MovieModel) =
        movieDao.insert(movieModel)

    override fun deleteFavoriteMovie(movieModel: MovieModel): Int =
        movieDao.delete(movieModel)

}