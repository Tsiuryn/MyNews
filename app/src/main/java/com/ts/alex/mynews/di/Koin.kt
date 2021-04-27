package com.ts.alex.mynews.di

import com.ts.alex.mynews.data.datasource.IDBDataSource
import com.ts.alex.mynews.data.datasource.IGetNewsDataSource
import com.ts.alex.mynews.data.datasource.ISharedPreferencesDataSource
import com.ts.alex.mynews.data.datasource.impl.DBDataSource
import com.ts.alex.mynews.data.datasource.impl.GetNewsDataSource
import com.ts.alex.mynews.data.datasource.impl.SharedPreferencesDataSource
import com.ts.alex.mynews.data.local.preferences.SharedPreference
import com.ts.alex.mynews.data.network.RestApi
import com.ts.alex.mynews.data.network.providePlaceHolderApi
import com.ts.alex.mynews.data.repository.DBRepository
import com.ts.alex.mynews.data.repository.GetNewsRepository
import com.ts.alex.mynews.data.repository.SharedPreferencesRepository
import com.ts.alex.mynews.domain.usecase.IDBUseCase
import com.ts.alex.mynews.domain.usecase.IGetNewsUseCase
import com.ts.alex.mynews.domain.usecase.ISharedPreferencesUseCase
import com.ts.alex.mynews.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val singleModule = module {
    viewModel { MainViewModel(getNews = get(), db = get(), preferences = get()) }

    factory { GetNewsRepository(getNewsDataSource = get()) as IGetNewsUseCase }
    factory { DBRepository(source = get()) as IDBUseCase }
    factory { SharedPreferencesRepository(source = get()) as ISharedPreferencesUseCase }

    factory { GetNewsDataSource(restApi = get()) as IGetNewsDataSource }
    factory { DBDataSource() as IDBDataSource }
    factory { SharedPreferencesDataSource(preferences = get()) as ISharedPreferencesDataSource }

    single { providePlaceHolderApi() as RestApi }
    single { SharedPreference(context = get()) }
}