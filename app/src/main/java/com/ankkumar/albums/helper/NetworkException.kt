package com.ankkumar.albums.helper

import android.content.Context
import java.io.IOException

class NetworkException(context: Context) : IOException() {
    private val mContext: Context = context

    override val message: String?
        get() = "No Internet"
}