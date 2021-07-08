package com.atech.data.repositoriesimpl

import com.atech.data.db.MovieDao
import com.atech.data.mapper.DetailMovieMapper
import com.atech.data.mapper.MovieMapper
import com.atech.data.mapper.ReviewMapper
import com.atech.data.mapper.VideoMapper
import com.atech.data.remote.MovieApi
import com.atech.domain.entities.DetailMovieModel
import com.atech.domain.entities.MovieModel
import com.atech.domain.entities.ReviewModel
import com.atech.domain.entities.VideoModel
import com.atech.domain.repositories.MovieRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Abraham Lay on 2020-06-09.
 */


class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val movieDao: MovieDao,
    private val movieMapper: MovieMapper,
    private val reviewMapper: ReviewMapper,
    private val videoMapper: VideoMapper,
    private val detailMovieMapper: DetailMovieMapper
) :
    MovieRepository {

    override fun getPopularMovies(apiKey: String): Flowable<List<MovieModel>?> =
        api.getPopularMovies(apiKey).map(movieMapper)

    override fun getTopRatedMovies(apiKey: String): Flowable<List<MovieModel>?> =
        api.getTopRatedMovies(apiKey).map(movieMapper)

    override fun getNowPlayingMovies(apiKey: String): Flowable<List<MovieModel>?> =
        api.getNowPlayingMovies(apiKey).map(movieMapper)

    override fun getReviews(apiKey: String, movieId: Int): Flowable<List<ReviewModel>> =
        api.getReviews(movieId, apiKey).map(reviewMapper)

    override fun getVideo(apiKey: String, movieId: Int): Flowable<List<VideoModel>> =
        api.getVideo(movieId, apiKey).map(videoMapper)

    override fun getMovieDetails(apiKey: String, movieId: Int): Flowable<DetailMovieModel> =
        api.getMovieDetails(movieId, apiKey).map(detailMovieMapper)

    override fun getFavoriteMovies(): Flowable<List<MovieModel>?> =
        movieDao.selectFavoriteMovie()

    override fun getFavoriteMovie(movieId: Int): Flowable<MovieModel?> =
        movieDao.select(movieId)

    override fun insertFavoriteMovie(movieModel: MovieModel) =
        movieDao.insert(movieModel)

    override fun deleteFavoriteMovie(movieModel: MovieModel): Int =
        movieDao.delete(movieModel)
}