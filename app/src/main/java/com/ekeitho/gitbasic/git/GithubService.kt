package com.ekeitho.gitbasic.git

import androidx.annotation.WorkerThread
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @WorkerThread
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Observable<List<Repo>>

}