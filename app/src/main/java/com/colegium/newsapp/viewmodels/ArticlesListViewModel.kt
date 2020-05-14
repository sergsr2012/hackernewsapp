package com.colegium.newsapp.viewmodels

import android.os.Bundle
import android.support.v4.app.INotificationSideChannel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.colegium.newsapp.domain.GetArticlesListIUseCase
import com.colegium.newsapp.domain.ReloadArticlesFromRemoteIUseCase
import com.colegium.newsapp.domain.RemoveArticleFromListUseCase
import com.colegium.newsapp.viewmodels.dataclasses.Response
import com.colegium.newsapp.viewmodels.helpers.DisposablesManager

class ArticlesListViewModel(
    private val getArticlesListIUseCase: GetArticlesListIUseCase,
    private val reloadArticlesFromRemoteIUseCase: ReloadArticlesFromRemoteIUseCase,
    private val removeArticleFromListUseCase: RemoveArticleFromListUseCase,
    private val disposablesManager: DisposablesManager
) : ViewModel() {

    fun removeArticleFromlist(id: Int) {
        removeArticleFromListUseCase.request(id)
    }

    fun reloadArticles() {
        disposablesManager.addDisposable(reloadArticlesFromRemoteIUseCase.request(), "reload")
    }

    fun getAllArticles() {
        disposablesManager.addDisposableFlowable(getArticlesListIUseCase.request(), "request_all_articles")
    }

    fun getLiveData(): MutableLiveData<Response> {
        return disposablesManager.liveData
    }
}