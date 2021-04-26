package com.ts.alex.mynews.domain.usecase

import com.ts.alex.mynews.domain.entity.news.News

interface IGetNewsUseCase {

    suspend fun getNewsByCountryDomain(domain: String): News
}