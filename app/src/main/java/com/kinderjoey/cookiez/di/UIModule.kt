package com.kinderjoey.cookiez.di

import com.kinderjoey.cookiez.adapter.*
import com.kinderjoey.cookiez.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}

val adapterModule = module {
    single { CategoryAdapter() }
    single { LeaderboardAdapter() }
    single { ListMenuAdapter() }
    single { PreviewTransactionAdapter() }
    single { PromotionAdapter() }
    single { SelectedCategoryAdapter(get()) }
}