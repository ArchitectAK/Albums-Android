package com.ankkumar.albums.di

import android.content.Context
import com.ankkumar.albums.AlbumApplication
import com.ankkumar.albums.retrofit.AlbumService
import com.ankkumar.albums.retrofit.Repository
import com.ankkumar.albums.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class, NetworkModule::class, DatabaseModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideAppContext(application: AlbumApplication): Context {
        return application.applicationContext

    }


    @Provides
    @Singleton
    fun provideRepo(apiService: AlbumService, database: AppDatabase): Repository {
        return Repository(apiService, database)
    }


}
