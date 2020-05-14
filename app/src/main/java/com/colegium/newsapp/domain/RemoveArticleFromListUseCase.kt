package com.colegium.newsapp.domain

import com.colegium.newsapp.domain.interfaces.IArticlesRepository

class RemoveArticleFromListUseCase (
    private val articlesRepository: IArticlesRepository
) {
    fun request(id: Int) {
        articlesRepository.updateIsRemovedPropertyArticlesById(id)
    }
}