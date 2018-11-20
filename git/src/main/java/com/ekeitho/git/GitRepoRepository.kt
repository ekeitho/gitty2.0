package com.ekeitho.git

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ekeitho.git.db.Repo
import com.ekeitho.git.db.RepoDao
import com.ekeitho.git.db.RepoRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GitRepoRepository(application: Application, private val githubService: GithubService) {

    private val repoDao: RepoDao
    private val repoLiveData: MutableLiveData<List<Repo>>


    init {
        val db = RepoRoomDatabase.getDatabase(application) as RepoRoomDatabase
        repoDao = db.repoDao()
        repoLiveData = MutableLiveData()
    }

    fun setupRepoListLiveData(): LiveData<List<Repo>> {
        return repoLiveData
    }

    // logic here can differ based on use case
    suspend fun loadAllRepos() {
        withContext(Dispatchers.IO) {
            repoLiveData.value = githubService.listRepos("ekeitho").await()
        }

        /*
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
         */
    }


}