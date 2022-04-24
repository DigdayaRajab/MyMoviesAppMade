package com.rajabd.di

import com.rajabd.favorite.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}
