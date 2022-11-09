package com.putri.photoapp.network

import com.squareup.moshi.Json

// class MarsPhoto yang berisi id dengan tipe data string dan URL gambar
data class MarsPhoto(
        val id: String,

        // digunakan untuk menempatkan img_src dari JSON ke imgSrcUrl
        @Json(name = "img_src") val imgSrcUrl: String
)