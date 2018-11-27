package com.ekeitho.git

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ekeitho.git.db.Repo
import com.ekeitho.git.db.RepoDao
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GitViewModelTest {

    // Live Data will immediately post values without switching threads
    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    lateinit var viewModel: GitViewModel


    lateinit var repo: GitRepoRepository

    val gitRepoDao = mock(RepoDao::class.java)
    val gitService = mock(GithubService::class.java)


    @Before
    fun setup() {
        repo = spy(GitRepoRepository(gitRepoDao, gitService))
        viewModel = GitViewModel(repo)
    }

    @Test
    fun onHappyAppStart_showBar() {
        val newRepoList = listOf(Repo("android mvvm"))

        `when`(gitRepoDao.getAllRepos()).thenReturn(newRepoList)
        viewModel.loadRepositories()


        runBlocking {
            // make sure repo has called load repos
            verify(repo).loadAllRepos()

            viewModel.repos.captureValues {
                assertSendsValues(2_000, newRepoList)
            }
        }
    }
}