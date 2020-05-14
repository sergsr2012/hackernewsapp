package com.colegium.newsapp.domain.mappers

import com.colegium.newsapp.domain.model.Article
import com.colegium.newsapp.domain.interfaces.IArticle

fun mapArticelRepro( art:IArticle) : Article {
    return with(art) {
        Article(id, title, author, created_at, url, false, 0)
    }
}