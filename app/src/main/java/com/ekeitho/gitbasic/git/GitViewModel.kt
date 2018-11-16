package com.ekeitho.gitbasic.git


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ekeitho.gitbasic.git.db.RepoRepository
import io.reactivex.disposables.Disposables

class GitViewModel : ViewModel() {

    lateinit var users: LiveData<List<Repo>>

    private var disposable = Disposables.empty()

    fun getUsers(repoRepository: RepoRepository) {
        if (!::users.isInitialized) {
            users = repoRepository.getAllRepos()
            disposable = repoRepository.loadAllRepos()
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}

