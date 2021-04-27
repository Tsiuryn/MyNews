package com.ts.alex.mynews.data.datasource

interface ISharedPreferencesDataSource {

    fun isUpdateNews(): Boolean

    fun setUpdateNews(isUpdate: Boolean)

    fun updateTime(): Long

    fun setUpdateTime(updateTime: Long)
}