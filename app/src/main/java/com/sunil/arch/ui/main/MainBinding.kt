package com.sunil.arch.ui.main

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sunil.arch.data.MovieEntity
import com.sunil.arch.remote.repository.Resource

object MainBinding {

    @BindingAdapter("app:items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, resource: Resource<List<MovieEntity>>?) {
        with(recyclerView.adapter as MainAdapter) {
            resource?.data?.let { updateData(it) }
        }
    }

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImageUrl(view: ImageView, url: String) {
        Glide.with(view.context).load(url).into(view)
    }

    @BindingAdapter("app:showWhenEmptyList")
    @JvmStatic
    fun showMessageErrorWhenEmptyList(view: View, resource: Resource<List<MovieEntity>>?) {
        if (resource != null) {
            view.visibility = if (resource.status == Resource.Status.ERROR
                && resource.data != null
                && resource.data.isEmpty()
            ) View.VISIBLE else View.GONE
        }
    }

}