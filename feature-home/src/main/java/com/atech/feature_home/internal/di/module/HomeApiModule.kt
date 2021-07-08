package com.atech.feature_home.internal.di.module

import com.atech.data.db.MovieDao
import com.atech.data.mapper.DetailMovieMapper
import com.atech.data.mapper.MovieMapper
import com.atech.data.mapper.ReviewMapper
import com.atech.data.mapper.VideoMapper
import com.atech.data.remote.MovieApi
import com.atech.data.repositoriesimpl.MovieRepositoryImpl
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