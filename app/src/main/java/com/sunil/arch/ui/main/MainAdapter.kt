package com.sunil.arch.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sunil.arch.R
import com.sunil.arch.data.MovieEntity
import com.sunil.arch.utility.MainItemDiffCallback
import com.sunil.arch.viewModel.MainViewModel

class MainAdapter(private val viewModel: MainViewModel): RecyclerView.Adapter<MainViewHolder>(){

    private val movies: MutableList<MovieEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false))

    override fun getItemCount(): Int
            = movies.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int)
            = holder.bindTo(movies[position], viewModel)

    fun updateData(items: List<MovieEntity>) {
        val diffCallback = MainItemDiffCallback(movies, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        movies.clear()
        movies.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }
}