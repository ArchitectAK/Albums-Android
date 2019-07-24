package com.ankkumar.albums.room


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ankkumar.albums.model.AlbumEntity

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun savePost(entityList: List<AlbumEntity>)

    @Query("SELECT * FROM AlbumEntity")
    fun getPosts(): LiveData<List<AlbumEntity>>


}