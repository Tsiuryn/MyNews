package com.ts.alex.mynews.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ts.alex.mynews.data.local.database.TypeDataBase
import com.ts.alex.mynews.domain.entity.news.Article
import com.ts.alex.mynews.domain.entity.news.News
import com.ts.alex.mynews.domain.usecase.IDBUseCase
import com.ts.alex.mynews.domain.usecase.IGetNewsUseCase
import com.ts.alex.mynews.domain.usecase.IJobUseCase
import com.ts.alex.mynews.domain.usecase.ISharedPreferencesUseCase
import com.ts.alex.mynews.ui.util.CountryDomain
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(
    private val getNews: IGetNewsUseCase,
    private val db: IDBUseCase,
    private val preferences: ISharedPreferencesUseCase,
    private val job: IJobUseCase
) : ViewModel() {

    private var article: Article? = null

    var positionScroll = 0

    private var _newsByCountry = MutableLiveData<List<Article>?>()
    val newsByCountry: LiveData<List<Article>?>
        get() = _newsByCountry

    private var _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private var _updateNews = MutableLiveData<Pair<Long, Boolean>>()
    val updateNews: LiveData<Pair<Long, Boolean>>
        get() = _updateNews

    fun getNewsByCountry(domain: CountryDomain) {
        viewModelScope.launch {
            try {
                val news = getNews.getNewsByCountryDomain(domain.domain)
                _newsByCountry.value = news.articles
                updateDB(domain, news)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    private fun updateDB(domain: CountryDomain, news: News) {
        when(domain){
            CountryDomain.FRANCE ->{
                db.removeAllData(TypeDataBase.FRENCH_DATABASE)
                db.addAll(news, TypeDataBase.FRENCH_DATABASE)
            }
            CountryDomain.RUSSIA ->{
                db.removeAllData(TypeDataBase.RUSSIAN_DATABASE)
                db.addAll(news, TypeDataBase.RUSSIAN_DATABASE)
            }
            CountryDomain.USA ->{
                db.removeAllData(TypeDataBase.USA_DATABASE)
                db.addAll(news, TypeDataBase.USA_DATABASE)
            }
        }
    }

    fun setArticle(article: Article) {
        this.article = article
    }

    fun getArticle() = article

    fun getNewsFromDB(countryDomain: CountryDomain) {
        try {
            when(countryDomain){
                CountryDomain.FRANCE -> {
                    Log.d("TAG11", "getNewsFromDB: get Data From DB")
                    _newsByCountry.value = db.getAllData(TypeDataBase.FRENCH_DATABASE)
                }
                CountryDomain.RUSSIA ->{
                    _newsByCountry.value = db.getAllData(TypeDataBase.RUSSIAN_DATABASE)
                }
                CountryDomain.USA ->{
                    _newsByCountry.value = db.getAllData(TypeDataBase.USA_DATABASE)
                }
            }
        }catch (e: Exception){}
    }

    fun cleanListArticle(){
        _newsByCountry.value = null
    }

    fun updateNews(mlsTime: Long){
        preferences.setUpdateNews(isUpdate = true)
        preferences.setUpdateTime(mlsTime)
        _updateNews.value = preferences.updateTime()  to preferences.isUpdateNews()
    }

    fun cancelUpdateNews(){
        preferences.setUpdateNews(isUpdate = false)
        _updateNews.value = preferences.updateTime() to preferences.isUpdateNews()
    }

    fun isUpdateNews() = preferences.isUpdateNews()

    fun startJob(){
        if(preferences.isUpdateNews()){
            job.startJob(preferences.updateTime())
        }
    }

    fun cancelJob(){
        job.cancelJob()
    }
}