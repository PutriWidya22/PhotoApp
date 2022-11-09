package com.putri.photoapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.putri.photoapp.network.MarsApi
import com.putri.photoapp.network.MarsPhoto
import kotlinx.coroutines.launch

enum class MarsApiStatus { LOADING, ERROR, DONE }

// membuat class OverviewViewModel yang mengextends class ViewModel
class OverviewViewModel : ViewModel() {


    // MutableLiveData internal yang menyimpan status permintaan terbaru.
    private val _status = MutableLiveData<MarsApiStatus>()

    // LiveData eksternal yang tidak dapat diubah untuk status permintaan.
    val status: LiveData<MarsApiStatus> = _status

    // menambahkan properti dengan nama _photos, MutableLiveData dapat menyimpan objek MarsPhoto
    // tunggal.
    private val _photos = MutableLiveData<List<MarsPhoto>>()

    // pada LiveData eksternal ke properti tidak dapat diubah, hanya kelas tsb yang dapat
    // memodifikasi
    val photos: LiveData<List<MarsPhoto>> = _photos

    // memanggil getMarsPhotos() pada init agar dapat segera menampilkan status.
    init {
        getMarsPhotos()
    }

    // membuat function getMarsPhotos() untuk mendapatkan informasi foto Mars dari layanan Mars API
    // Retrofit dan memperbaruinya.
    private fun getMarsPhotos() {

        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                _photos.value = MarsApi.retrofitService.getPhotos()
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }
}
