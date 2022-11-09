package com.putri.photoapp.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.putri.photoapp.databinding.FragmentOverviewBinding

// membuat class OverviewFragment,  Fragmen ini menunjukkan status transaksi layanan web foto Mars.
class OverviewFragment : Fragment() {

    // deklarasi variabel viewModel
    private val viewModel: OverviewViewModel by viewModels()

    // function onCreateView untuk mengembangkan tata letak dengan data binding.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        // memungkinkan binding untuk mengamati LiveData lifecycleOwner pada fragmemt.
        binding.lifecycleOwner = this

        // memberikan akses binding ke OverViewModel.
        binding.viewModel = viewModel

        // mengeset adapter photosGrid
        binding.photosGrid.adapter = PhotoGridAdapter()

        // mengembalikan nilai binding
        return binding.root
    }
}
