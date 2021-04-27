package com.ts.alex.mynews.data.local.database.entity

import com.ts.alex.mynews.data.local.database.entity.models.ArticleDB
import com.ts.alex.mynews.data.local.database.entity.models.convertToArticleDB
import com.ts.alex.mynews.domain.entity.news.News
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
data class DBEntity(
    @Id
    var id: Long = 0,


    var typeDB: Int? = null,

    val status: String? = null,

    val totalResults: Int? = null
) {
     lateinit var articles: ToMany<ArticleDB>
}

internal fun News.convertToFrenchDB(): DBEntity{
    val entity = DBEntity(
        status = this.status,
        totalResults = this.totalResults
    )
    this.articles.forEach {
        entity.articles.add(it.convertToArticleDB())
    }
    return entity

}