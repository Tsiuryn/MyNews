package com.ts.alex.mynews.data.datasource.impl

import com.ts.alex.mynews.data.datasource.IFrenchDBDataSource
import com.ts.alex.mynews.data.local.database.NewsDataBase
import com.ts.alex.mynews.data.local.database.entity.FrenchDBEntity
import io.objectbox.Box

class FrenchDBDataSource: IFrenchDBDataSource {
    override fun getAllData(): FrenchDBEntity {
       val db =  getDataBase().all
        return db[0]
    }

    override fun removeAllData() {
        getDataBase().removeAll()
    }

    override fun addAll(entity: FrenchDBEntity) {
        getDataBase().put(entity)
    }

    private fun getDataBase(): Box<FrenchDBEntity> {
        return NewsDataBase.get().boxFor(FrenchDBEntity::class.java)
    }
}