package com.colegium.newsapp.data.repositories

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.colegium.newsapp.data.db.ArticleDao
import com.colegium.newsapp.data.helpers.BoundaryCallBackArticlesList
import com.colegium.newsapp.data.mappers.mapArticleRequestToArticle
import com.colegium.newsapp.data.model.local.Article
import com.colegium.newsapp.data.model.local.DataState
import com.colegium.newsapp.data.model.remote.ArticleListRequest
import com.colegium.newsapp.data.model.remote.ArticleRequest
import com.colegium.newsapp.data.remote.ApiNewsClient
import com.colegium.newsapp.domain.interfaces.IArticlesRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

private const val CONST_PAGE_SIZE_DEFAULT: Int = 10
private const val CONST_INITIAL_LOAD__SIZE_DEFAULT: Int = 20

class ArticlesRepository(
    private val newsClient: ApiNewsClient,
    private val articleDao: ArticleDao,
    private val dataState :DataState,
    private val disposables : CompositeDisposable
): IArticlesRepository {

    override fun getAllArticles(): Flowable<PagedList<Article>> {
        val localPagedList = getLocalPagedArticles()
        return localPagedList.flatMap { pagedList->
            if (pagedList.size == 0 ) {
                fetchRemoteArticles(0)
                    .map { pagedList }
                    .toFlowable(BackpressureStrategy.DROP)
            } else {
                Flowable.just(pagedList)
            }
        }.onErrorResumeNext { x: Throwable ->  getLocalPagedArticles() }
    }

    override fun reloadArticlesListFromRemote(): Observable<List<Article>> {
        return fetchRemoteArticles(0)
    }

    override fun updateIsRemovedPropertyArticlesById(id: Int) {
        addPublishable(Flowable.fromCallable{articleDao.updateIsRemovedById(id, true)}, "")
    }

    private fun <T : Any> addPublishable(flowable: Flowable<T>, request: String?) {
        disposables.add(flowable.subscribeOn(Schedulers.io()).doOnError {
            throw it
        }.subscribe({}, {
            //Send error to Colegium analytics tool
        }))
    }

    private fun getLocalPagedArticles(): Flowable<PagedList<Article>> {
        val config = PagedList.Config.Builder()
            .setPageSize(CONST_PAGE_SIZE_DEFAULT)
            .setInitialLoadSizeHint(CONST_INITIAL_LOAD__SIZE_DEFAULT)
            .setEnablePlaceholders(true)
            .build()

        val factoryLocalDataSource = articleDao.getAllArticlesPaged()
        return RxPagedListBuilder<Int, Article>(factoryLocalDataSource, config)
            .setBoundaryCallback(BoundaryCallBackArticlesList(this))
            .buildFlowable(BackpressureStrategy.DROP).observeOn(Schedulers.io())
    }

    private fun fetchRemoteArticles(nPage: Int): Observable<List<Article>> {
        return newsClient.getArticles(nPage).map { request ->
            var newList: List<Article>? = null
            try {
                newList =  request.hits.map {
                    mapArticleRequestToArticle(it, request.page)
                }
                articleDao.insertArticles(newList)
                dataState.cacheWasUpdated = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
            newList
        }
    }

    internal fun getNewArticlesPage(page: Int) {
        addPublishable(fetchRemoteArticles(page).toFlowable(BackpressureStrategy.DROP), "new page")
    }

    fun getart():Observable<ArticleListRequest> {
        val a = ArrayList<ArticleRequest>()
        for (i in 0..15){
            a.add(
            ArticleRequest(
                i.toString(),"a " + i,false,"",""+ i, "" , "", 0))
        }
        return Observable.just(ArticleListRequest(a,0))
    }

}