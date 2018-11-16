package com.ekeitho.gitbasic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.ekeitho.gitbasic.RepoApplication
import com.ekeitho.gitbasic.databinding.RepoFragmentBinding
import com.ekeitho.gitbasic.di.DaggerApplicationComponent
import com.ekeitho.gitbasic.git.GitViewModel
import com.ekeitho.gitbasic.git.GithubService
import com.ekeitho.gitbasic.git.db.RepoRepository
import javax.inject.Inject


class GitReposFragment : androidx.fragment.app.Fragment() {

    @Inject
    lateinit var repo: RepoRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        (context?.applicationContext as RepoApplication)
            .getAppComponents()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val db = RepoFragmentBinding.inflate(inflater, container, false)
        db.setLifecycleOwner(this)

        val vm = ViewModelProviders.of(this).get(GitViewModel::class.java)
        db.vm = vm
        return db.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewModelProviders.of(this).get(GitViewModel::class.java).apply {
            getUsers(repo)
        }
    }
}