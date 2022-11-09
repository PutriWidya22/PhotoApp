package com.putri.photoapp.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.putri.photoapp.databinding.GridViewItemBinding
import com.putri.photoapp.network.MarsPhoto

// membuat class PhotoGridAdapter yang mengimplementasikan RecyclerView, ListAdapter yang
// menggunakan data binding untuk menampilkan list data.
class PhotoGridAdapter :
    ListAdapter<MarsPhoto, PhotoGridAdapter.MarsPhotosViewHolder>(DiffCallback) {

    // konstruktor MarsPhotosViewHolder mengambil variabel binding dari GridViewItemBindin,
    // lalu GridViewItemBinding memberikan akses ke informasi MarsPhoto.
    class MarsPhotosViewHolder(
        private var binding: GridViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marsPhoto: MarsPhoto) {
            binding.photo = marsPhoto
            // memungkinkan RecyclerView membuat ukuran tampilan.
            binding.executePendingBindings()
        }
    }

    // memungkinkan RecyclerView untuk menentukan item mana yang telah berubah saat diperbaru dari
    // MarsPhoto.
    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }

    // function onCreateViewHolder untuk membuat tampilan item baru.
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarsPhotosViewHolder {
        return MarsPhotosViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    // function onBindViewHolder untuk mengganti konten tampilan
    override fun onBindViewHolder(holder: MarsPhotosViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }
}
