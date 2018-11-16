package com.ekeitho.gitbasic.ui

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ekeitho.git.db.Repo

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("liveData")
    fun bindToRecycler(recyclerView: RecyclerView, userLiveData: LiveData<List<Repo>>) {
        recyclerView.adapter = GitRepoAdapter(userLiveData)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    }
}