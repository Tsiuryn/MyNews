package com.ts.alex.mynews.data.network.entity

import com.google.gson.annotations.SerializedName

data class ArticleEntity(

    @SerializedName("author")
    val author: String? ,

    @SerializedName("content")
    val content: String? ,

    @SerializedName("description")
    val description: String?,

    @SerializedName("publishedAt")
    val publishedAt: String,

    @SerializedName("source")
    val source: SourceEntity,

    @SerializedName("title")
    val title: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("urlToImage")
    val urlToImage: String?
)

internal fun ArticleEntity.convertToDomainArticle(): com.ts.alex.mynews.domain.entity.news.Article {
    return com.ts.alex.mynews.domain.entity.news.Article(
        author = this.author ?: "",
        content = this.content ?: "",
        description = this.description ?: "",
        publishedAt = this.publishedAt,
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage ?: ""
    )
}