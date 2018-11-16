package com.ekeitho.gitbasic.di

import android.app.Application
import com.ekeitho.git.GitRepoRepository
import com.ekeitho.git.GithubService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class ApplicationModule {

    val application: Application

    constructor(application: Application) {
        this.application = application
    }


    @Provides
    fun getApp(): Application {
        return this.application
    }

    @Provides
    fun githubService(): GithubService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(GithubService::class.java)
    }

    @Provides
    fun repoRepository(application: Application, githubService: GithubService): GitRepoRepository {
        return GitRepoRepository(application, githubService)
    }
}