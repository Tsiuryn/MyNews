package com.ts.alex.mynews.domain.usecase

import com.ts.alex.mynews.data.local.database.TypeDataBase
import com.ts.alex.mynews.domain.entity.news.Article
import com.ts.alex.mynews.domain.entity.news.News

interface IDBUseCase {

    fun getAllData(typeDataBase: TypeDataBase): List<Article>

    fun removeAllData(typeDataBase: TypeDataBase)

    fun addAll(entity: News, typeDataBase: TypeDataBase)
}