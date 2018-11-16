package com.ekeitho.git


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ekeitho.git.db.Repo
import io.reactivex.disposables.Disposables

class GitViewModel : ViewModel() {

    lateinit var users: LiveData<List<Repo>>


    private var disposable = Disposables.empty()

    fun getUsers(gitRepoRepository: GitRepoRepository) {
        if (!::users.isInitialized) {
            users = gitRepoRepository.getAllRepos()
            disposable = gitRepoRepository.loadAllRepos()
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}

