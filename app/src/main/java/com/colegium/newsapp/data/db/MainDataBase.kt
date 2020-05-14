package com.colegium.newsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.colegium.newsapp.data.model.local.Article

@Database(entities = [Article::class],
    version = 1,
    exportSchema = false)
abstract class MainDataBase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}