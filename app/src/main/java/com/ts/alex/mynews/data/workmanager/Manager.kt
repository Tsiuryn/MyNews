package com.ts.alex.mynews.data.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.ts.alex.mynews.data.datasource.impl.DBDataSource
import com.ts.alex.mynews.data.local.database.TypeDataBase
import com.ts.alex.mynews.data.local.database.entity.convertToDBEntity
import com.ts.alex.mynews.data.network.entity.convertToDomainNews
import com.ts.alex.mynews.data.network.providePlaceHolderApi
import com.ts.alex.mynews.ui.util.CountryDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Manager(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {


    override fun doWork(): Result {
        makeStatusNotification("We will update news in your phone", applicationContext)
        updateNewsDB()
        return Result.success()
    }

    private fun updateNewsDB(){
        val frenchNews =
            providePlaceHolderApi().getNewsByCountry(
                CountryDomain.FRANCE.domain
            )
        val russianNews =
            providePlaceHolderApi().getNewsByCountry(
                CountryDomain.RUSSIA.domain
            )
        val usaNews =
            providePlaceHolderApi().getNewsByCountry(
                CountryDomain.USA.domain
            )
        GlobalScope.launch {
            withContext(Dispatchers.Default){
                DBDataSource().removeAllData(TypeDataBase.FRENCH_DATABASE)
                val frNews = frenchNews.await().convertToDomainNews().convertToDBEntity()
                frNews.typeDB = TypeDataBase.FRENCH_DATABASE.value
                DBDataSource().addAll(frNews)

                DBDataSource().removeAllData(TypeDataBase.RUSSIAN_DATABASE)
                val rusNews = russianNews.await().convertToDomainNews().convertToDBEntity()
                rusNews.typeDB = TypeDataBase.RUSSIAN_DATABASE.value
                DBDataSource().addAll(rusNews)

                DBDataSource().removeAllData(TypeDataBase.USA_DATABASE)
                val usaNews = usaNews.await().convertToDomainNews().convertToDBEntity()
                usaNews.typeDB = TypeDataBase.USA_DATABASE.value
                DBDataSource().addAll(usaNews)

            }

        }
    }
}