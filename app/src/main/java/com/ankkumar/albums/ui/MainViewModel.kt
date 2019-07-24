package com.ankkumar.albums.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ankkumar.albums.model.AlbumEntity
import com.ankkumar.albums.retrofit.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val postsData = MutableLiveData<List<AlbumEntity>>()
    fun getPosts() {
        repository.getAlbums().enqueue(object : Callback<List<AlbumEntity>> {
            override fun onFailure(call: Call<List<AlbumEntity>>, t: Throwable) {
                postsData.value = null
            }

            override fun onResponse(
                call: Call<List<AlbumEntity>>,
                response: Response<List<AlbumEntity>>
            ) {
                if (response.isSuccessful) {
                    postsData.value = response.body()?.sortedBy { it.title }
                }
            }
        })
    }

    fun getPostsObserver(): LiveData<List<AlbumEntity>> = postsData


    fun saveAlbumsToDB(entityList: List<AlbumEntity>) {
        repository.savePostInDB(entityList)
    }

    fun getPostsFromDB(): LiveData<List<AlbumEntity>> {
        return repository.getPostsFromLocalDB()
    }

}