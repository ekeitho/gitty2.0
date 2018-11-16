package com.ekeitho.gitbasic.di

import com.ekeitho.gitbasic.fragment.GitReposFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
public interface ApplicationComponent {
    fun inject(repoFragment: GitReposFragment)
}