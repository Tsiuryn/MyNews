package com.ts.alex.mynews.data.local.database.entity.models

import com.ts.alex.mynews.domain.entity.news.Article
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class ArticleDB(
    @Id
    var id: Long = 0,

    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
)

internal fun Article.convertToArticleDB(): ArticleDB {
    return ArticleDB(
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage

    )
}

internal fun ArticleDB.convertToArticle(): Article {
    return Article(
        author = this.author?:"",
        content = this.content?:"",
        description = this.description?:"",
        publishedAt = this.publishedAt?:"",
        title = this.title?:"",
        url = this.url?:"",
        urlToImage = this.urlToImage?:"",
    )
}