package com.ankkumar.albums.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AlbumEntity")
data class AlbumEntity(@PrimaryKey val id: Int, val userId: Int, val title: String)