package com.atech.base.di.module

import androidx.lifecycle.ViewModelProvider
import com.squline.common.base.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory?): ViewModelProvider.Factory?
}