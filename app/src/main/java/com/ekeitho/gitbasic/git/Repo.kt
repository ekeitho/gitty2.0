package com.ekeitho.gitbasic.git

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo_table")
data class Repo(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "fullName")
    val full_name: String)

