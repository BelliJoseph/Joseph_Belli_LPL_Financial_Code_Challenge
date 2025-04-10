package com.example.lplfinancialcodechallenge.data.repository

import com.example.lplfinancialcodechallenge.data.entity.PostEntityModel
import com.example.lplfinancialcodechallenge.data.network.PostNetworkModel
import com.example.lplfinancialcodechallenge.data.service.PostsService
import com.example.lplfinancialcodechallenge.database.PostsDao
import retrofit2.Response
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val service: PostsService,
    private val postsDao: PostsDao,
): PostsRepository {

    override suspend fun getNetworkPosts(): Response<List<PostNetworkModel>> =
        service.callPostsApi()

    override suspend fun insertPost(postEntityModel: PostEntityModel) =
        postsDao.insertPost(postEntityModel = postEntityModel)

    override suspend fun insertAllPostsDB(entityList: List<PostEntityModel>) {
        postsDao.insertAllPosts(entityList)
    }

    override suspend fun getAllPostsDB(): List<PostEntityModel> =
        postsDao.getAllPosts()

}