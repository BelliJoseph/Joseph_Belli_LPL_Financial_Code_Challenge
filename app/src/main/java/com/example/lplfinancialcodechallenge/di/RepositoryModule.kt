package com.example.lplfinancialcodechallenge.di

import com.example.lplfinancialcodechallenge.data.repository.PostsRepository
import com.example.lplfinancialcodechallenge.data.repository.PostsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsPostsRepository(
        postsRepositoryImpl: PostsRepositoryImpl
    ): PostsRepository

}