package com.colegium.newsapp.data.mappers

import com.colegium.newsapp.data.model.local.Article
import com.colegium.newsapp.data.model.remote.ArticleRequest
import java.lang.NullPointerException

//Modify this class when Colegium back end change its payload response
internal fun mapArticleRequestToArticle(articleRequest: ArticleRequest, page: Int): Article {
    return articleRequest.run {
        Article(
            0,
            story_title ?: title ?: throw NullPointerException(),
            author ?: "No autor",
            created_at ?: "NA",
            story_url  ?: url ?: "NA",
            false,
            page
        )
    }
}