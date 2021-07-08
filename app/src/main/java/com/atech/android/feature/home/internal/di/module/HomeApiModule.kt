package com.atech.android.feature.home.internal.di.module

import com.atech.android.MyApplication
import com.atech.android.base.util.SessionHelper
import com.atech.android.base.util.StubUtil
import com.atech.android.data.datasource.MovieDataSourceImpl
import com.atech.android.data.datasource.StubDataSourceImpl
import com.atech.android.data.db.MovieDao
import com.atech.android.data.mapper.DetailMovieMapper
import com.atech.android.data.mapper.MovieMapper
import com.atech.android.data.mapper.ReviewMapper
import com.atech.android.data.mapper.VideoMapper
import com.atech.android.data.remote.MovieApi
import com.atech.android.data.repositoriesimpl.MovieRepositoryImpl
import com.atech.android.domain.datasource.MovieDataSource
import com.atech.android.domain.datasource.StubDataSource
import com.atech.android.domain.repositories.MovieRepository
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
    fun provideStubDataSource(myApplication: MyApplication, stubUtil: StubUtil): StubDataSource =
        StubDataSourceImpl(myApplication.applicationContext, stubUtil)

    @Singleton
    @Provides
    fun provideMovieDataSource(movieApi: MovieApi, movieDao: MovieDao, sessionHelper: SessionHelper): MovieDataSource =
        MovieDataSourceImpl(
            movieApi, movieDao, sessionHelper
        )


    @Singleton
    @Provides
    fun provideMovieRepository(source: MovieDataSource, stubDataSource: StubDataSource): MovieRepository =
        MovieRepositoryImpl(
            source, stubDataSource, MovieMapper(), ReviewMapper(),
            VideoMapper(), DetailMovieMapper()
        )

    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(
        MovieApi::class.java
    )

}