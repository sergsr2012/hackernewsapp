package com.colegium.newsapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Article(
    val id: Int,
    val title: String,
    val author: String,
    val created_at: String,
    val url: String,
    val isRemoved: Boolean,
    val page: Int
)