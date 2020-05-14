package com.colegium.newsapp.domain.interfaces

interface IArticle {
    val id: Int
    val title: String
    val author: String
    val created_at: String
    val url: String
    val isRemoved: Boolean
    val page: Int
}