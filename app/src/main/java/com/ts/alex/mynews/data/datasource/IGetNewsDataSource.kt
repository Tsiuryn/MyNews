package com.ts.alex.mynews.data.datasource

import com.ts.alex.mynews.data.network.entity.NewsEntity

interface IGetNewsDataSource {

    suspend fun getNewsByCountryDomain(domain: String): NewsEntity
}