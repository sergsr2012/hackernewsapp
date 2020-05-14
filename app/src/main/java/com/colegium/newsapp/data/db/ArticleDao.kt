package com.colegium.newsapp.data.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.colegium.newsapp.data.model.local.Article
import java.util.*

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertArticles(articles: List<Article>): List<Long>

    @Query("SELECT * FROM articles WHERE isRemoved = 0 ORDER BY created_at DESC")
    fun getAllArticlesPaged(): DataSource.Factory<Int, Article>

    @Query("UPDATE articles SET isRemoved = :isRemoved WHERE id = :id")
    fun updateIsRemovedById(id: Int, isRemoved: Boolean)
}
