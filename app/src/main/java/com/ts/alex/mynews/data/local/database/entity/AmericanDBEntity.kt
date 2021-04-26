package com.ts.alex.mynews.data.local.database.entity

import com.ts.alex.mynews.data.local.database.entity.models.ArticleDB
import com.ts.alex.mynews.data.local.database.entity.models.convertToArticleDB
import com.ts.alex.mynews.domain.entity.news.Article
import com.ts.alex.mynews.domain.entity.news.News
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class AmericanDBEntity(

    val articles: List<ArticleDB>,

    val status: String,

    val totalResults: Int
) {
    @Id
    var id: Long = 0
}

internal fun News.convertToAmericanDB(): AmericanDBEntity{
    return AmericanDBEntity(
        articles = this.articles.map { it.convertToArticleDB() },
        status = this.status,
        totalResults = this.totalResults
    )
}