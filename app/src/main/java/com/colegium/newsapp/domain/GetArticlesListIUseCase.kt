package com.colegium.newsapp.domain

import androidx.paging.PagedList
import com.colegium.newsapp.data.model.local.Article
import com.colegium.newsapp.domain.interfaces.IArticlesRepository
import io.reactivex.Flowable

class GetArticlesListIUseCase(
    private val articlesRepository: IArticlesRepository
){
    fun request(): Flowable<PagedList<Article>> {
        return articlesRepository.getAllArticles()
    }
}