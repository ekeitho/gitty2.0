package com.ekeitho.gitbasic.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ekeitho.gitbasic.databinding.ItemViewBinding
import com.ekeitho.gitbasic.fragment.GitReposFragmentDirections
import com.ekeitho.gitbasic.git.Repo


class UserAdapter(val liveData: LiveData<List<Repo>>): RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val db = ItemViewBinding.inflate(LayoutInflater.from(parent.context))
        return UserViewHolder(db)
    }

    override fun getItemCount(): Int {
        return liveData.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        liveData.value?.get(position)?.apply {
            holder.itemViewBinding.textView.text = this.full_name

            holder.itemViewBinding.root.setOnClickListener {
                val poo = GitReposFragmentDirections.repoClickAction()
                poo.setRepoName(this.full_name)
                it.findNavController().navigate(poo)
            }
        }
    }
}


class UserViewHolder: RecyclerView.ViewHolder {

    val itemViewBinding: ItemViewBinding

    constructor(itemViewBinding: ItemViewBinding): super(itemViewBinding.root) {
        this.itemViewBinding = itemViewBinding;
    }
}