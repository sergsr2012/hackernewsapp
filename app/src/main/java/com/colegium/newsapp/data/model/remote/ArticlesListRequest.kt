package com.colegium.newsapp.data.model.remote

import com.colegium.newsapp.data.model.local.Article

data class ArticleListRequest(
    val hits: List<ArticleRequest>,
    val page: Int
)