package com.sunil.arch.utility

import androidx.recyclerview.widget.DiffUtil
import com.sunil.arch.data.MovieEntity

class MainItemDiffCallback(private val oldList: List<MovieEntity>,
                           private val newList: List<MovieEntity>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int)
            = oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].posterPath == newList[newItemPosition].posterPath
                && oldList[oldItemPosition].title == newList[newItemPosition].title
    }
}