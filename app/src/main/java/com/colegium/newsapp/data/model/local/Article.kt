package com.colegium.newsapp.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.colegium.newsapp.domain.interfaces.IArticle

@Entity(tableName = "articles", indices = arrayOf(Index(value = ["title", "author", "url"],
    unique = true)))
data class Article(
    @PrimaryKey(autoGenerate = true)
    override val id: Int,
    @ColumnInfo override val title: String,
    @ColumnInfo override val author: String,
    @ColumnInfo override val created_at: String,
    @ColumnInfo override val url: String,
    @ColumnInfo override val isRemoved: Boolean,
    @ColumnInfo override val page: Int
): IArticle