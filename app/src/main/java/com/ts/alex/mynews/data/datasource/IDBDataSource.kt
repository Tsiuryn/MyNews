package com.ts.alex.mynews.data.datasource

import com.ts.alex.mynews.data.local.database.TypeDataBase
import com.ts.alex.mynews.data.local.database.entity.DBEntity

interface IDBDataSource {

    fun getAllData(typeDataBase: TypeDataBase): DBEntity?

    fun removeAllData(typeDataBase: TypeDataBase)

    fun addAll(entity: DBEntity)
}