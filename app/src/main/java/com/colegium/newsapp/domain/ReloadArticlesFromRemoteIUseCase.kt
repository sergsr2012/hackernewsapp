package com.colegium.newsapp.domain

import com.colegium.newsapp.domain.model.Article
import com.colegium.newsapp.domain.interfaces.IArticlesRepository
import com.colegium.newsapp.domain.mappers.mapArticelRepro
import io.reactivex.Observable

class ReloadArticlesFromRemoteIUseCase (
    private val articlesRepository: IArticlesRepository
) {
    fun request(): Observable<List<Article>> {
        return articlesRepository.reloadArticlesListFromRemote().map {
            it.map { art ->
                mapArticelRepro(art)
            }
        }
    }
}