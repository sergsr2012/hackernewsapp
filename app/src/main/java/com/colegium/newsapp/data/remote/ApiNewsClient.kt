package com.colegium.newsapp.data.remote

import com.colegium.newsapp.data.model.remote.ArticleListRequest
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiNewsClient {
    @GET("api/v1/search_by_date?query=android")
    fun getArticles(@Query("page") page: Int): Observable<ArticleListRequest>
}