package com.putri.photoapp

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.putri.photoapp.network.MarsPhoto
import com.putri.photoapp.overview.MarsApiStatus
import com.putri.photoapp.overview.PhotoGridAdapter

// memperbarui data yang ditampilkan di RecylerView
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

// menggunakan perpustakaan coil untuk memuat gambar dengan URL ke dalam ImageView.
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

// BindingAdapter menampilkan MarsApiStatus. ketika gambar sedang dimuat akan menampilkan status
// LOADING, jika gambar terdapat kesalahan maka akan menampilkan status ERROR, dan jika gambar telah
// ditampilkan maka akan menampilkan status DONE.
@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: MarsApiStatus?) {
    when (status) {
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
