package com.ekeitho.gitbasic.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class DaggerViewModelFactory

    /*
        Without @JvmSuppressWildcards
        get error ->

            error: java.util.Map<java.lang.Class<? extends androidx.lifecycle.ViewModel>,?
                        extends javax.inject.Provider<androidx.lifecycle.ViewModel>> cannot be provided without an @Provides-annotated method.
                            public abstract void inject(@org.jetbrains.annotations.NotNull()

     */

    @Inject constructor(private val viewModelsMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = viewModelsMap[modelClass] ?: viewModelsMap.asIterable().firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return try {
            // unchecked cast here
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}