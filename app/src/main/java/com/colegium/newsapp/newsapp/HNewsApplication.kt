package com.colegium.newsapp.newsapp

import android.app.Application
import com.colegium.newsapp.newsapp.di.newsModule
import org.koin.android.ext.android.startKoin

class HNewsApplication : Application() {

    override fun onCreate(){
        super.onCreate()
        // Start Koin
        startKoin(this, listOf(newsModule))
    }
}