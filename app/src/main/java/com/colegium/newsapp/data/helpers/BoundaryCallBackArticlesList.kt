package com.colegium.newsapp.data.helpers

import androidx.paging.PagedList
import com.colegium.newsapp.data.model.local.Article
import com.colegium.newsapp.data.repositories.ArticlesRepository

class BoundaryCallBackArticlesList(val articlesRepository: ArticlesRepository) : PagedList.BoundaryCallback<Article>() {

    override fun onItemAtEndLoaded(itemAtEnd: Article) {
        if (itemAtEnd.page < 10) {
            articlesRepository.getNewArticlesPage(itemAtEnd.page+1)
        }
    }
}