package com.ekeitho.gitbasic.git.db

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ekeitho.gitbasic.git.GithubService
import com.ekeitho.gitbasic.git.Repo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RepoRepository {

    private val repoDao: RepoDao
    private val githubService: GithubService
    private val repoLiveData: MutableLiveData<List<Repo>>

    constructor(application: Application, githubService: GithubService) {
        val db = RepoRoomDatabase.getDatabase(application) as RepoRoomDatabase
        repoDao = db.repoDao()
        repoLiveData = MutableLiveData()
        this.githubService = githubService
    }

    fun getAllRepos(): LiveData<List<Repo>> {
        return repoLiveData
    }


    // logic here can differ based on use case
    fun loadAllRepos(): Disposable {
        return repoDao.getAllRepos()
            .switchMap {
                if (it.count() == 0) {
                    githubService
                        .listRepos("ekeitho")
                        .map { repos ->
                            for (repo in repos) {
                                repoDao.insert(repo)
                            }
                            repos
                        }
                } else {
                    Observable.just(it)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                repoLiveData.value = it
            }
    }


}