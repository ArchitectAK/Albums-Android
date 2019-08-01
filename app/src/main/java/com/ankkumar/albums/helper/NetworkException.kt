package com.ankkumar.albums.helper

import java.io.IOException

class NetworkException : IOException() {

    override val message: String?
        get() = "No Internet"
}