package com.ankkumar.albums.di

import android.content.Context
import androidx.room.Room
import com.ankkumar.albums.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    private var INSTANCE: AppDatabase? = null

    @Provides
    @Singleton
    fun getDatabase(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class.java) {
                if (INSTANCE == null) {
                    INSTANCE =
                        Room.databaseBuilder(context, AppDatabase::class.java, "albums_db").build()

                }
            }
        }
        return INSTANCE!!
    }


}