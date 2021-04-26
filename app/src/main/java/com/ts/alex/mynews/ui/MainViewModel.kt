package com.ts.alex.mynews.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ts.alex.mynews.data.local.database.entity.convertToFrenchDB
import com.ts.alex.mynews.data.local.database.entity.models.convertToArticle
import com.ts.alex.mynews.domain.entity.news.Article
import com.ts.alex.mynews.domain.entity.news.News
import com.ts.alex.mynews.domain.usecase.IFrenchDBUseCase
import com.ts.alex.mynews.domain.usecase.IGetNewsUseCase
import com.ts.alex.mynews.ui.util.CountryDomain
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(
    private val getNews: IGetNewsUseCase,
    private val frenchDB: IFrenchDBUseCase
) : ViewModel() {

    private var article: Article? = null

    var positionScroll = 0

    private var _newsByCountry = MutableLiveData<List<Article>?>()
    val newsByCountry: LiveData<List<Article>?>
        get() = _newsByCountry

    private var _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

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
                frenchDB.removeAllData()
                frenchDB.addAll(news)
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
                CountryDomain.FRANCE ->{
                    _newsByCountry.value = frenchDB.getAllData()

                }
            }
        }catch (e: Exception){}
    }

    fun cleanListArticle(){
        _newsByCountry.value = null
    }

}