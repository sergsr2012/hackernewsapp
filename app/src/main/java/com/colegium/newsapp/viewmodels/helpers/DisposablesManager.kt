package com.colegium.newsapp.viewmodels.helpers

import androidx.lifecycle.MutableLiveData
import com.colegium.newsapp.viewmodels.dataclasses.*
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class DisposablesManager {

    private val disposables = CompositeDisposable()
    val liveData = MutableLiveData<Response>()


    fun <T : Any> addDisposable(observable: Observable<T>, request: String?) {
        disposables.add(observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                liveData.postValue(Response.loading())
            }
            .subscribe({
                liveData.postValue(Response.success(it, request))
            }, {
                val error = parseErrorResponse(it, request ?: "")
                liveData.postValue(Response.error(error, request))
            }))
    }

    fun <T : Any> addDisposableFlowable(flowable: Flowable<T>, request: String?) {
        disposables.add(flowable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                liveData.postValue(Response.loading())
            }
            .subscribe({
                liveData.postValue(Response.success(it, request))
            }, {
                val error = parseErrorResponse(it, request ?: "")
                //Iim showing all the error beacouse newsapp is a test app
                liveData.postValue(Response.error(error, request))
            }))
    }

    fun clearDisposables() {
        disposables.clear()
    }

    private fun parseErrorResponse(throwable: Throwable, request: String): ErrorType {
        return when (throwable) {
            is HttpException -> ErrorType(HTTP_ERROR, message = throwable.message ?: "", request = request)
            is UnknownHostException -> ErrorType(NETWORK_ERROR, message = throwable.message ?: "", request = request)
            is SocketTimeoutException -> ErrorType(NETWORK_ERROR, message = throwable.message ?: "", request = request)
            is ConnectException -> ErrorType(NETWORK_ERROR, message = throwable.message ?: "", request = request)
            else -> ErrorType(UNKNOWN, message = throwable.message ?: "", request = request)
        }
    }

}