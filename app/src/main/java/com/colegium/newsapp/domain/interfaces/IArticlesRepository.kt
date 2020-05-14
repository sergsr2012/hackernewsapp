package com.colegium.newsapp.domain.interfaces

import androidx.paging.PagedList
import com.colegium.newsapp.data.model.local.Article
import io.reactivex.Flowable
import io.reactivex.Observable

interface IArticlesRepository {
    fun getAllArticles(): Flowable<PagedList<Article>>

    fun reloadArticlesListFromRemote(): Observable<List<Article>>

    fun updateIsRemovedPropertyArticlesById(id: Int)
}