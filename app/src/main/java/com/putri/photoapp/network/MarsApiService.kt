package com.putri.photoapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

// membangun objek Moshi yang akan digunakan Retrofit, dan menambahkan adapter kotlin.
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// menggunakan Retrofit untuk membuat objek retroit dengan konverter Moshi.
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// interface MarsApiService dengan method GET
interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}

// Objek MarsApi yab=ng mengekspos retrofit yang diinisialisasi dengan lazy.
object MarsApi {
    val retrofitService: MarsApiService by lazy { retrofit.create(MarsApiService::class.java) }
}
