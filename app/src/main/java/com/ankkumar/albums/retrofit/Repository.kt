package com.ankkumar.albums.retrofit

import androidx.lifecycle.LiveData
import com.ankkumar.albums.model.AlbumEntity
import com.ankkumar.albums.room.AppDatabase
import retrofit2.Call


class Repository constructor(
    private val apiService: AlbumService,
    private val database: AppDatabase
) {

    fun getAlbums(): Call<List<AlbumEntity>> {
        return apiService.getAlbums()
    }

    fun savePostInDB(entityList: List<AlbumEntity>) {
        val thread = Thread(Runnable { database.postDAO().savePost(entityList) })
        thread.start()
    }

    fun getPostsFromLocalDB(): LiveData<List<AlbumEntity>> {
        return database.postDAO().getPosts()
    }


}