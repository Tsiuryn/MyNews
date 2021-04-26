package com.ts.alex.mynews.data.datasource

import com.ts.alex.mynews.data.local.database.entity.FrenchDBEntity

interface IFrenchDBDataSource {

    fun getAllData(): FrenchDBEntity

    fun removeAllData()

    fun addAll(entity: FrenchDBEntity)
}