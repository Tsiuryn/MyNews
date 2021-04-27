package com.ts.alex.mynews.data.local.preferences

import android.content.Context

class SharedPreference(private val context: Context) {
    private val PREFERENCES = "com.ts.alex.data.local.prererences.preferences"

    private val UPDATE_NEWS = "com.ts.alex.data.local.prererences.update_news"
    fun updateNews(): Boolean {
        val preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        return preferences.getBoolean(UPDATE_NEWS, false)
    }

    fun setUpdateNews (isUpdate: Boolean) {
        val editor = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        editor.edit().putBoolean(UPDATE_NEWS, isUpdate).apply()
    }

    private val UPDATE_TIME = "com.ts.alex.data.local.prererences.update_time"
    fun updateTime(): Long {
        val preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        return preferences.getLong(UPDATE_TIME, 15 * 60 * 1000)
    }

    fun setUpdateTime (updateTime: Long) {
        val editor = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        editor.edit().putLong(UPDATE_TIME, updateTime).apply()
    }

}