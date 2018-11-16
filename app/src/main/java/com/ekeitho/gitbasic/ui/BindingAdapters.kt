package com.ekeitho.gitbasic.ui

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ekeitho.gitbasic.git.Repo
import com.ekeitho.gitbasic.ui.UserAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("bind:liveData")
    fun bindToRecycler(recyclerView: RecyclerView, userLiveData: LiveData<List<Repo>>) {
        recyclerView.adapter = UserAdapter(userLiveData)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    }
}