package com.example.lplfinancialcodechallenge.data.repository

import com.example.lplfinancialcodechallenge.data.entity.PostEntityModel
import com.example.lplfinancialcodechallenge.data.network.PostNetworkModel
import retrofit2.Response

interface PostsRepository {

    suspend fun getNetworkPosts(): Response<List<PostNetworkModel>>

    suspend fun insertPost(postEntityModel: PostEntityModel)

    suspend fun insertAllPostsDB(entityList: List<PostEntityModel>)

    suspend fun getAllPostsDB(): List<PostEntityModel>
}