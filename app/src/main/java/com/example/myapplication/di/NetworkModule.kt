package com.example.myapplication.di

import android.content.Context
import android.util.Log
import com.example.myapplication.BuildConfig
import com.example.myapplication.utils.NetworkInterceptor
import com.example.myapplication.data.network.ApiService
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideNetworkInterceptor(): NetworkInterceptor =
        NetworkInterceptor()

    @Provides
    fun provideOkHttpInstance(
        @ApplicationContext context: Context,
        interceptor: Interceptor,
        apiInterceptor: NetworkInterceptor
    ): OkHttpClient {
        val cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
//            .addNetworkInterceptor(StethoInterceptor())
            .addNetworkInterceptor(interceptor)
            .cookieJar(cookieJar)
            .addInterceptor(apiInterceptor)
            .protocols(listOf(Protocol.HTTP_1_1, Protocol.HTTP_2))
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    fun provideLoggingInterceptorInstance(): Interceptor {
        val logging = HttpLoggingInterceptor { message ->
            Log.e(
                "NetworkModule",
                "\n$message"
            )
        }
        logging.setLevel(
            if (BuildConfig.DEBUG)
                Level.BODY else Level.NONE
        )
        return logging
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}