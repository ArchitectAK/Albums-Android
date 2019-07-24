package com.ankkumar.albums.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ankkumar.albums.model.AlbumEntity


@Database(entities = [AlbumEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDAO(): AlbumDao

}
