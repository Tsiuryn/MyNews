package com.ts.alex.mynews.domain.usecase

interface ISharedPreferencesUseCase {

    fun isUpdateNews(): Boolean

    fun setUpdateNews(isUpdate: Boolean)

    fun updateTime(): Long

    fun setUpdateTime(updateTime: Long)
}