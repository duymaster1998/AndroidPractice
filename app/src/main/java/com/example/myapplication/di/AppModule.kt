package com.example.myapplication.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGsonInstance(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    fun provideRequestManager(
        @ApplicationContext context: Context,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(context)
            .setDefaultRequestOptions(requestOptions)
    }

    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions()
            .placeholder(R.drawable.shadow_bottom_to_top)
            .error(R.drawable.shadow_bottom_to_top)
    }
}