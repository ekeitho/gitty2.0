package com.ekeitho.gitbasic.git.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ekeitho.gitbasic.git.Repo

@Database(entities = arrayOf(Repo::class), version = 1)
abstract class RepoRoomDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao

    companion object {
        private var database: RoomDatabase? = null

        fun getDatabase(context: Context): RoomDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    RepoRoomDatabase::class.java, "repo_database"
                ).allowMainThreadQueries().build()
            }
            return database!!
        }
    }
}