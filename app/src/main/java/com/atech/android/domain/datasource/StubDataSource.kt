package com.atech.android.domain.datasource

import com.atech.android.data.dtos.MovieDto
import io.reactivex.Flowable

interface StubDataSource {
    fun getPopularMovies(): Flowable<MovieDto>
    fun getTopRatedMovies(): Flowable<MovieDto>
    fun getNowPlayingMovies(): Flowable<MovieDto>
}