package com.ankkumar.albums.di

import com.ankkumar.albums.AlbumApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBindingModule::class])
interface AppComponent : AndroidInjector<AlbumApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AlbumApplication>()

}
