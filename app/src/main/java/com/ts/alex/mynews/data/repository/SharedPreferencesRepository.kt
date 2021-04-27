package com.ts.alex.mynews.data.repository

import com.ts.alex.mynews.data.datasource.ISharedPreferencesDataSource
import com.ts.alex.mynews.domain.usecase.ISharedPreferencesUseCase

class SharedPreferencesRepository (val source: ISharedPreferencesDataSource): ISharedPreferencesUseCase {
    override fun isUpdateNews(): Boolean {
        return source.isUpdateNews()
    }

    override fun setUpdateNews(isUpdate: Boolean) {
        source.setUpdateNews(isUpdate)
    }

    override fun updateTime(): Long {
        return source.updateTime()
    }

    override fun setUpdateTime(updateTime: Long) {
        source.setUpdateTime(updateTime)
    }
}