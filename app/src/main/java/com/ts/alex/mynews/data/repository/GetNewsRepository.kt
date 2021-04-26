package com.ts.alex.mynews.data.repository

import com.ts.alex.mynews.data.datasource.IGetNewsDataSource
import com.ts.alex.mynews.data.network.entity.convertToDomainNews
import com.ts.alex.mynews.domain.entity.news.News
import com.ts.alex.mynews.domain.usecase.IGetNewsUseCase

class GetNewsRepository(private val getNewsDataSource: IGetNewsDataSource) : IGetNewsUseCase {

    override suspend fun getNewsByCountryDomain(domain: String): News {
        return getNewsDataSource.getNewsByCountryDomain(domain).convertToDomainNews()
    }
}