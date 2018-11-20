package com.ekeitho.git.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Repo::class), version = 1, exportSchema = false)
abstract class RepoRoomDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao

    companion object {
        private var database: RoomDatabase? = null

        fun getDatabase(context: Context): RoomDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    RepoRoomDatabase::class.java, "repo_database"
                ).build()
            }
            return database!!
        }
    }

}