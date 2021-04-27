package com.ts.alex.mynews.data.datasource.impl

import com.ts.alex.mynews.data.datasource.ISharedPreferencesDataSource
import com.ts.alex.mynews.data.local.preferences.SharedPreference

class SharedPreferencesDataSource (private val preferences: SharedPreference): ISharedPreferencesDataSource {
    override fun isUpdateNews(): Boolean {
        return preferences.updateNews()
    }

    override fun setUpdateNews(isUpdate: Boolean) {
        preferences.setUpdateNews(isUpdate)
    }

    override fun updateTime(): Long {
        return preferences.updateTime()
    }

    override fun setUpdateTime(updateTime: Long) {
        preferences.setUpdateTime(updateTime)
    }
}