package com.colegium.newsapp.data.model.remote

import androidx.room.ColumnInfo
import androidx.room.Entity

data class ArticleRequest(
    @ColumnInfo val story_title: String?,
    @ColumnInfo val author: String?,
    @ColumnInfo val isDeleted: Boolean?,
    @ColumnInfo val story_url: String?,
    @ColumnInfo val created_at: String?,
    @ColumnInfo val title: String?,
    @ColumnInfo val url: String?,
    @ColumnInfo val page: Int?
)