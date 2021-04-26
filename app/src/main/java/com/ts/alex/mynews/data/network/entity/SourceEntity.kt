package com.ts.alex.mynews.data.network.entity

import com.google.gson.annotations.SerializedName

data class SourceEntity(

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("name")
    val name: String
)

internal fun SourceEntity.convertToDomainSource(): com.ts.alex.mynews.domain.entity.news.Source {
    return com.ts.alex.mynews.domain.entity.news.Source(
        id = this.id?: "",
        name = this.name
    )
}