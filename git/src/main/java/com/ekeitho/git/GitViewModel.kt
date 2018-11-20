package com.ekeitho.git


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ekeitho.git.db.Repo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GitViewModel : ViewModel() {

    lateinit var repos: LiveData<List<Repo>>

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    fun getUsers(gitRepoRepository: GitRepoRepository) {
        if (!::repos.isInitialized) {
            repos = gitRepoRepository.setupRepoListLiveData()

            uiScope.launch {
                gitRepoRepository.loadAllRepos()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}

