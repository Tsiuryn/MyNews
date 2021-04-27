package com.ts.alex.mynews.di

import com.ts.alex.mynews.data.datasource.IDBDataSource
import com.ts.alex.mynews.data.datasource.IGetNewsDataSource
import com.ts.alex.mynews.data.datasource.impl.DBDataSource
import com.ts.alex.mynews.data.datasource.impl.GetNewsDataSource
import com.ts.alex.mynews.data.network.RestApi
import com.ts.alex.mynews.data.network.providePlaceHolderApi
import com.ts.alex.mynews.data.repository.DBRepository
import com.ts.alex.mynews.data.repository.GetNewsRepository
import com.ts.alex.mynews.domain.usecase.IDBUseCase
import com.ts.alex.mynews.domain.usecase.IGetNewsUseCase
import com.ts.alex.mynews.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val singleModule = module {
    viewModel { MainViewModel(getNews = get(), db = get()) }

    factory { GetNewsRepository(getNewsDataSource = get()) as IGetNewsUseCase }
    factory { DBRepository(source = get()) as IDBUseCase }

    factory { GetNewsDataSource(restApi = get()) as IGetNewsDataSource }
    factory { DBDataSource() as IDBDataSource }

    single { providePlaceHolderApi() as RestApi }
}