package com.ekeitho.git

import android.app.Application
import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
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

    @MainThread
    suspend fun updateRepoData(repos: List<Repo>) {
        withContext(Dispatchers.Main) {
            repoLiveData.value = repos
        }
    }

    // logic here can differ based on use case
    @WorkerThread
    suspend fun loadAllRepos() {
        withContext(Dispatchers.IO) {

            val dbResults = repoDao.getAllRepos()
            if (dbResults.isEmpty()) {
                try {
                    val list = githubService.listRepos("ekeitho").await()
                    for (repo in list) {
                        repoDao.insert(repo)
                    }
                    updateRepoData(list)
                } catch (exc: Exception) {
                    Log.e(GitRepoRepository::class.simpleName, "Caught Network Error", exc)
                }

            } else {
                updateRepoData(dbResults)
            }
        }

        /*

           more readable - but a lot more overhead!


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