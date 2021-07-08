package com.atech.android.feature.home.internal.di.module

import com.atech.android.data.db.MovieDao
import com.atech.android.data.mapper.DetailMovieMapper
import com.atech.android.data.mapper.MovieMapper
import com.atech.android.data.mapper.ReviewMapper
import com.atech.android.data.mapper.VideoMapper
import com.atech.android.data.remote.MovieApi
import com.atech.android.data.repositoriesimpl.MovieRepositoryImpl
import com.atech.domain.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeApiModule {
    @Singleton
    @Provides
    fun provideMovieRepository(movieApi: MovieApi, movieDao: MovieDao): MovieRepository =
        MovieRepositoryImpl(
            movieApi, movieDao, MovieMapper(), ReviewMapper(),
            VideoMapper(), DetailMovieMapper()
        )

    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(
        MovieApi::class.java
    )

}