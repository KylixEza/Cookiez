package com.kinderjoey.cookiez.di

import android.app.Application
import com.kinderjoey.cookiez.adapter.*
import com.kinderjoey.cookiez.ui.auth.login.LoginViewModel
import com.kinderjoey.cookiez.ui.auth.register.RegisterViewModel
import com.kinderjoey.cookiez.ui.category.CategoryViewModel
import com.kinderjoey.cookiez.ui.detail.DetailMenuViewModel
import com.kinderjoey.cookiez.ui.detail.menu.DetailMenuAboutViewModel
import com.kinderjoey.cookiez.ui.detail.menu.DetailMenuReviewViewModel
import com.kinderjoey.cookiez.ui.detail.menu.DetailMenuTutorialViewModel
import com.kinderjoey.cookiez.ui.detail.order.DetailVariantMenuViewModel
import com.kinderjoey.cookiez.ui.favorite.FavoriteViewModel
import com.kinderjoey.cookiez.ui.home.HomeViewModel
import com.kinderjoey.cookiez.ui.profile.ProfileViewModel
import com.kinderjoey.cookiez.ui.splash.SplashViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { CategoryViewModel(get()) }
    viewModel { DetailMenuViewModel(get()) }
    viewModel { DetailMenuAboutViewModel(get()) }
    viewModel { DetailMenuReviewViewModel(get()) }
    viewModel { DetailMenuTutorialViewModel(get()) }
    viewModel { DetailVariantMenuViewModel(get()) }
    viewModel { RegisterViewModel(get(), androidApplication()) }
    viewModel { LoginViewModel(get(), androidApplication()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { SplashViewModel(androidApplication()) }
}

val adapterModule = module {
    single { CategoryAdapter() }
    single { LeaderboardAdapter() }
    single { PreviewTransactionAdapter() }
    single { PromotionAdapter() }
    single { ReviewAdapter() }
    single { StepAdapter() }
    single { IngredientAdapter() }
    single { FavoriteAdapter() }
}