package com.example.wechatclone.di

import com.example.wechatclone.api.MomentApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    private val client = OkHttpClient.Builder().build()
    private const val BASE_URL = "https://thoughtworks-mobile-2018.herokuapp.com"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideMomentApi(retrofit: Retrofit): MomentApi =
        retrofit.create(MomentApi::class.java)
}
