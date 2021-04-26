package com.ts.alex.mynews.data.repository

import com.ts.alex.mynews.data.datasource.IFrenchDBDataSource
import com.ts.alex.mynews.data.local.database.entity.FrenchDBEntity
import com.ts.alex.mynews.data.local.database.entity.convertToFrenchDB
import com.ts.alex.mynews.data.local.database.entity.models.convertToArticle
import com.ts.alex.mynews.domain.entity.news.Article
import com.ts.alex.mynews.domain.entity.news.News
import com.ts.alex.mynews.domain.usecase.IFrenchDBUseCase

class FrenchDBRepository(val source: IFrenchDBDataSource): IFrenchDBUseCase {

    override fun getAllData(): List<Article> {
        return source.getAllData().articles.toList().map { it.convertToArticle() }
    }

    override fun removeAllData() {
        source.removeAllData()
    }

    override fun addAll(entity: News) {
        source.addAll(entity.convertToFrenchDB())
    }
}