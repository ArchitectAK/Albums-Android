package com.ankkumar.albums.di.module

import android.content.Context
import com.ankkumar.albums.helper.CheckNetwork
import com.ankkumar.albums.helper.NetworkException
import com.ankkumar.albums.helper.NetworkInterceptor
import com.ankkumar.albums.retrofit.AlbumService
import com.ankkumar.albums.utils.Utils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideGson(context: Context): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Utils.BASE_URL)
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context): OkHttpClient {
        val logging = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)

        client.addInterceptor(logging)

        client.addInterceptor((object : NetworkInterceptor() {
            override val isInternetAvailable: Boolean
                get() = CheckNetwork.isNetworkConnected(context)

            override fun onInternetUnavailable() {
                throw NetworkException(context)
            }
        }))


        return client.build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): AlbumService {
        return retrofit.create(AlbumService::class.java)
    }


}