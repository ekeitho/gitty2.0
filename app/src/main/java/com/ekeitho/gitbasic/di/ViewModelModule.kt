package com.ekeitho.gitbasic.di

import androidx.lifecycle.ViewModel
import com.ekeitho.git.GitViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(GitViewModel::class)
    abstract fun bindMyViewModel(gitViewModel: GitViewModel): ViewModel
}