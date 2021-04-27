package com.ts.alex.mynews.data.repository

import com.ts.alex.mynews.data.datasource.IDBDataSource
import com.ts.alex.mynews.data.local.database.TypeDataBase
import com.ts.alex.mynews.data.local.database.entity.convertToFrenchDB
import com.ts.alex.mynews.data.local.database.entity.models.convertToArticle
import com.ts.alex.mynews.domain.entity.news.Article
import com.ts.alex.mynews.domain.entity.news.News
import com.ts.alex.mynews.domain.usecase.IDBUseCase

class DBRepository(val source: IDBDataSource) : IDBUseCase {

    override fun getAllData(typeDataBase: TypeDataBase): List<Article> {
        val entity = source.getAllData(typeDataBase)
        return if (entity != null) {
            source.getAllData(typeDataBase)!!.articles.toList().map { it.convertToArticle() }
        } else emptyList()
    }

    override fun removeAllData(typeDataBase: TypeDataBase) {
        source.removeAllData(typeDataBase)
    }

    override fun addAll(entity: News, typeDataBase: TypeDataBase) {
        val db = entity.convertToFrenchDB()
        db.typeDB = typeDataBase.value
        source.addAll(db)
    }
}