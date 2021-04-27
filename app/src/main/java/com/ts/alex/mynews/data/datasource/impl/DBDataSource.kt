package com.ts.alex.mynews.data.datasource.impl

import com.ts.alex.mynews.data.datasource.IDBDataSource
import com.ts.alex.mynews.data.local.database.NewsDataBase
import com.ts.alex.mynews.data.local.database.TypeDataBase
import com.ts.alex.mynews.data.local.database.entity.DBEntity
import io.objectbox.Box

class DBDataSource: IDBDataSource {
    override fun getAllData(typeDataBase: TypeDataBase): DBEntity? {
       getDataBase().all.forEach{
           if(it.typeDB == typeDataBase.value){
               return it
           }
       }
        return null
    }

    override fun removeAllData(typeDataBase: TypeDataBase) {
        getDataBase().all.forEach{
            if(it.typeDB == typeDataBase.value){
                getDataBase().remove(it)
            }
        }

    }

    override fun addAll(entity: DBEntity) {
        getDataBase().put(entity)
    }

    private fun getDataBase(): Box<DBEntity> {
        return NewsDataBase.get().boxFor(DBEntity::class.java)
    }
}