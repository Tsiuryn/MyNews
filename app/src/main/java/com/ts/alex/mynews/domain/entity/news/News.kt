package com.ts.alex.mynews.domain.entity.news


data class News(

    val articles: List<Article>,

    val status: String,

    val totalResults: Int
)