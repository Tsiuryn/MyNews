package com.ts.alex.mynews.data.network.entity

import com.google.gson.annotations.SerializedName
import com.ts.alex.mynews.domain.entity.news.News

data class NewsEntity(

    @SerializedName("articles")
    val articles: List<ArticleEntity>,

    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: Int
)

internal fun NewsEntity.convertToDomainNews(): News {
    return News(
        articles = this.articles.map { it.convertToDomainArticle() },
        status = this.status,
        totalResults = this.totalResults
    )
}