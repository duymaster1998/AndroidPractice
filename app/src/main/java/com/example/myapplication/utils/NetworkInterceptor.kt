package com.example.myapplication.utils

import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMjIxODYzYzE1MjdkN2FlOTI2NGRiOWYxZjU1ZjdkYSIsIm5iZiI6MTcxOTM4NTQ5MS43NDAyNDIsInN1YiI6IjY2N2JiYmMyNDMyODc3NDVmOWY5YjYwZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.I44FhTUUYZdQDM3jyqUFxBIX2eo5pR9E8tkI5uS-OJw")
            .method(request.method, request.body)
            .build()

        return chain.proceed(newRequest)
    }
}