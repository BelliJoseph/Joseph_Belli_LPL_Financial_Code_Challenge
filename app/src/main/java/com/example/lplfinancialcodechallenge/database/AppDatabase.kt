package com.example.lplfinancialcodechallenge.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lplfinancialcodechallenge.data.entity.PostEntityModel

@Database(
    entities = [PostEntityModel::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
}