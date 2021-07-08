package com.atech.feature_home.internal.di.module

import androidx.lifecycle.ViewModel
import com.atech.feature_home.presentation.viewmodel.DetailViewModel
import com.atech.feature_home.presentation.viewmodel.HomeViewModel
import com.squline.common.base.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoMap

@Module
@InstallIn(FragmentComponent::class)
abstract class HomeViewModelModule {

    @Binds
    @IntoMap
    @ViewModelScoped
    @ViewModelKey(HomeViewModel::class)
    abstract fun provideHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelScoped
    @ViewModelKey(DetailViewModel::class)
    abstract fun provideDetailViewModel(viewModel: DetailViewModel): ViewModel
}