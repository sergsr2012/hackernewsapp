package com.colegium.newsapp.viewmodels.dataclasses

import androidx.annotation.StringDef

const val LOADING: String = "data_loading"
const val SUCCESS: String = "data_success"
const val ERROR: String = "data_error"
const val COMPLETED: String = "data_completed"

@Retention(AnnotationRetention.SOURCE)
@StringDef(LOADING, SUCCESS, ERROR, COMPLETED)
annotation class ResponseTypes