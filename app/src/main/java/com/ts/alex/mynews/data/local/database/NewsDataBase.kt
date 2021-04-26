package com.ts.alex.mynews.data.local.database

import android.content.Context
import com.ts.alex.mynews.data.local.database.entity.MyObjectBox
import io.objectbox.BoxStore

object NewsDataBase {

    private lateinit var boxStore: BoxStore
    fun init(context: Context) {
        boxStore = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()
    }

    fun get() = boxStore
}
