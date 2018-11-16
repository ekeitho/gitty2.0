package com.ekeitho.gitbasic.git.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ekeitho.gitbasic.git.Repo
import io.reactivex.Observable

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(repo: Repo)

    @Query("DELETE FROM repo_table")
    fun deleteAll()

    @Query("SELECT * from repo_table ORDER BY fullName ASC")
    fun getAllRepos(): Observable<List<Repo>>

}
