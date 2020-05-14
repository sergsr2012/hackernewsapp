package com.colegium.newsapp.viewmodels.dataclasses

class Response(@ResponseTypes val status: String, val data: Any?, val error: ErrorType?, val request: String?) {
    companion object {
        fun loading(): Response {
            return Response(LOADING, null, null, null)
        }

        fun success(data: Any, request: String?): Response {
            return Response(SUCCESS, data, null, request)
        }

        fun error(error: ErrorType, request: String?): Response {
            return Response(ERROR, null, error, request)
        }
    }
}