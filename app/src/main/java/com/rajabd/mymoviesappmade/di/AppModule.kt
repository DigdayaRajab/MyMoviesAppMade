package com.rajabd.mymoviesappmade.di

import com.rajabd.core.domain.usecase.MovieInteractor
import com.rajabd.core.domain.usecase.MovieUseCase
import com.rajabd.mymoviesappmade.detail.DetailViewModel
import com.rajabd.mymoviesappmade.home.HomeViewModel
import com.rajabd.mymoviesappmade.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
