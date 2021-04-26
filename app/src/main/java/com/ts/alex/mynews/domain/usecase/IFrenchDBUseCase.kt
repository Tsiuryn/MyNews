package com.ts.alex.mynews.domain.usecase

import com.ts.alex.mynews.data.local.database.entity.FrenchDBEntity
import com.ts.alex.mynews.domain.entity.news.Article
import com.ts.alex.mynews.domain.entity.news.News

interface IFrenchDBUseCase {

    fun getAllData(): List<Article>

    fun removeAllData()

    fun addAll(entity: News)
}