package com.ts.alex.mynews.di

import com.ts.alex.mynews.data.datasource.IFrenchDBDataSource
import com.ts.alex.mynews.data.datasource.IGetNewsDataSource
import com.ts.alex.mynews.data.datasource.impl.FrenchDBDataSource
import com.ts.alex.mynews.data.datasource.impl.GetNewsDataSource
import com.ts.alex.mynews.data.local.database.NewsDataBase
import com.ts.alex.mynews.data.network.RestApi
import com.ts.alex.mynews.data.network.providePlaceHolderApi
import com.ts.alex.mynews.data.repository.FrenchDBRepository
import com.ts.alex.mynews.data.repository.GetNewsRepository
import com.ts.alex.mynews.domain.usecase.IFrenchDBUseCase
import com.ts.alex.mynews.domain.usecase.IGetNewsUseCase
import com.ts.alex.mynews.ui.MainViewModel
import io.objectbox.BoxStore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val singleModule = module {
    viewModel { MainViewModel(getNews = get(), frenchDB = get()) }

    factory { GetNewsRepository(getNewsDataSource = get()) as IGetNewsUseCase }
    factory { FrenchDBRepository(source = get()) as IFrenchDBUseCase }

    factory { GetNewsDataSource(restApi = get()) as IGetNewsDataSource }
    factory { FrenchDBDataSource() as IFrenchDBDataSource }

    single { providePlaceHolderApi() as RestApi }
}