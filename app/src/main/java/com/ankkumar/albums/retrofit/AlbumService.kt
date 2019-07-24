package com.ankkumar.albums.retrofit


import com.ankkumar.albums.model.AlbumEntity
import com.ankkumar.albums.utils.Utils.ENDPOINT_ALBUM
import retrofit2.Call
import retrofit2.http.GET

interface AlbumService {
    @GET(value = ENDPOINT_ALBUM)
    fun getAlbums(): Call<List<AlbumEntity>>
}