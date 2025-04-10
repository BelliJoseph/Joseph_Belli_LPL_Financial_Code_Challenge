package com.example.lplfinancialcodechallenge.di

import android.content.Context
import androidx.room.Room
import com.example.lplfinancialcodechallenge.database.AppDatabase
import com.example.lplfinancialcodechallenge.database.PostsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "lpl_financial_database"
        ).build()
    }

    @Singleton
    @Provides
    fun providePostsDao(db: AppDatabase): PostsDao = db.postsDao()

}