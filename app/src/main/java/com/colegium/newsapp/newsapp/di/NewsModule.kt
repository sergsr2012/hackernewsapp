package com.colegium.newsapp.newsapp.di

import androidx.lifecycle.MutableLiveData
import androidx.room.Room.databaseBuilder
import com.colegium.newsapp.data.db.MainDataBase
import com.colegium.newsapp.data.model.local.DataState
import com.colegium.newsapp.data.remote.ApiNewsClient
import com.colegium.newsapp.data.repositories.ArticlesRepository
import com.colegium.newsapp.domain.GetArticlesListIUseCase
import com.colegium.newsapp.domain.ReloadArticlesFromRemoteIUseCase
import com.colegium.newsapp.domain.RemoveArticleFromListUseCase
import com.colegium.newsapp.domain.interfaces.IArticlesRepository
import com.colegium.newsapp.viewmodels.ArticlesListViewModel
import com.colegium.newsapp.viewmodels.dataclasses.Response
import com.colegium.newsapp.viewmodels.helpers.DisposablesManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val newsModule   = module {

    //todo move url string to better place
    single { Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(get()))
        .baseUrl("https://hn.algolia.com/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(ApiNewsClient::class.java) }

    single {
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setLenient().create()
    }

    single { databaseBuilder(get(), MainDataBase::class.java, "dbnewsapp")
        .fallbackToDestructiveMigration()
        .build() }

    single { get<MainDataBase>().articleDao() }

    factory { CompositeDisposable() }
    factory { MutableLiveData<Response>() }
    factory { DisposablesManager() }
    factory { DataState() }
    factory { ArticlesRepository(get(), get(), get(), get()) as IArticlesRepository }

    //UseCases
    factory { GetArticlesListIUseCase(get()) }
    factory { ReloadArticlesFromRemoteIUseCase(get()) }
    factory { RemoveArticleFromListUseCase(get()) }

    viewModel { ArticlesListViewModel(get(), get(), get(), get()) }

}