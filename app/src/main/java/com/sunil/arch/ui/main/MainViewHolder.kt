package com.sunil.arch.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sunil.arch.data.MovieEntity
import com.sunil.arch.databinding.ItemMainBinding
import com.sunil.arch.viewModel.MainViewModel

public class MainViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {

    private val binding = ItemMainBinding.bind(parent)

    fun bindTo(moveiEntity: MovieEntity, viewModel: MainViewModel) {
        binding.movie = moveiEntity
        binding.viewmodel = viewModel
    }
}