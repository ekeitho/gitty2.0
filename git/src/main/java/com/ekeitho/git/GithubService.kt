package com.ekeitho.git

import androidx.annotation.WorkerThread
import com.ekeitho.git.db.Repo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @WorkerThread
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Observable<List<Repo>>

}