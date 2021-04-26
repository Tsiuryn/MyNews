package com.ts.alex.mynews.data.local.database.entity

import com.ts.alex.mynews.data.local.database.entity.models.ArticleDB
import com.ts.alex.mynews.data.local.database.entity.models.convertToArticleDB
import com.ts.alex.mynews.domain.entity.news.Article
import com.ts.alex.mynews.domain.entity.news.News
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class RussianDBEntity(

    val articles: List<ArticleDB>,

    val status: String,

    val totalResults: Int
) {
    @Id
     var id: Long = 0
}

internal fun News.convertToRussianDB(): RussianDBEntity{
    return RussianDBEntity(
        articles = this.articles.map { it.convertToArticleDB() },
        status = this.status,
        totalResults = this.totalResults
    )
}