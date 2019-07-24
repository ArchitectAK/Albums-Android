package com.ankkumar.albums.model

import java.util.*

class AlbumResponse {

    var data: AlbumEntity? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        val albumResponse = other as AlbumResponse?
        return data == albumResponse?.data
    }

    override fun hashCode(): Int {
        return Objects.hash(data)
    }

    override fun toString(): String {
        return "SpotPrice{" +
                ", data=" + data +
                '}'.toString()
    }
}
