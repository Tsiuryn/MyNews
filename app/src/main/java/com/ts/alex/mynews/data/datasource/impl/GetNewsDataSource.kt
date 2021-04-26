package com.ts.alex.mynews.data.datasource.impl

import com.ts.alex.mynews.data.datasource.IGetNewsDataSource
import com.ts.alex.mynews.data.network.RestApi
import com.ts.alex.mynews.data.network.entity.NewsEntity

class GetNewsDataSource(private val restApi: RestApi): IGetNewsDataSource {

    override suspend fun getNewsByCountryDomain(domain: String): NewsEntity {
        return restApi.getNewsByCountry(domainCountry = domain).await()
    }
}