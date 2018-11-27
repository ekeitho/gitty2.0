package com.ekeitho.git

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ekeitho.git.db.Repo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


class GitViewModel @Inject constructor(private val gitRepoRepository: GitRepoRepository) : ViewModel() {

    /*
    * This variable is private because we don't want to expose MutableLiveData
    *
    * MutableLiveData allows anyone to set a value, and MainViewModel is the only
    * class that should be setting values.
    */
    private val _repos = MutableLiveData<List<Repo>>()

    val repos: LiveData<List<Repo>>
        get() = _repos

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    fun loadRepositories() {
        uiScope.launch {
            _repos.value = gitRepoRepository.loadAllRepos()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}

