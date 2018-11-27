package com.ekeitho.git

import androidx.annotation.WorkerThread
import com.ekeitho.git.db.Repo
import com.ekeitho.git.db.RepoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class GitRepoRepository(private val repoDao: RepoDao, private val githubService: GithubService) {

    // logic here can differ based on use case
    @WorkerThread
    open suspend fun loadAllRepos(): List<Repo> {
        return withContext(Dispatchers.IO) {
            val dbResults = repoDao.getAllRepos()
            if (dbResults.isEmpty()) {
                try {
                    val list = githubService.listRepos("ekeitho").await()
                    for (repo in list) {
                        repoDao.insert(repo)
                    }
                    list
                } catch (exc: Exception) {
                    //Log.e(GitRepoRepository::class.simpleName, "Caught Network Error", exc)
                    // log error
                    listOf<Repo>()
                }

            } else {
                dbResults
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