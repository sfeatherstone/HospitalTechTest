package com.sfeatherstone.sensynetechtest.repository.network

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.Reader


class RemoteFileReader {
    private var client = OkHttpClient()

    fun run(url: String): Reader? {
        val request = Request.Builder()
            .url(url)
            .build()
        return client.newCall(request).execute().body?.charStream()
    }
}