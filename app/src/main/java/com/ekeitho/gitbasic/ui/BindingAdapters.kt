package com.ekeitho.gitbasic.ui

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ekeitho.git.db.Repo

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("liveData")
    fun RecyclerView.bindToRecycler( userLiveData: LiveData<List<Repo>>) {
        this.adapter = GitRepoAdapter(userLiveData)
        this.layoutManager = LinearLayoutManager(this.context)
    }
}