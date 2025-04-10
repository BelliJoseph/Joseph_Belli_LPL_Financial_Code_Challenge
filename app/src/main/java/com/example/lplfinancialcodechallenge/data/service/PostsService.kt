package com.example.lplfinancialcodechallenge.data.service

import com.example.lplfinancialcodechallenge.data.PATH
import com.example.lplfinancialcodechallenge.data.network.PostNetworkModel
import retrofit2.Response
import retrofit2.http.GET

interface PostsService {
    
    @GET(PATH)
    suspend fun callPostsApi(): Response<List<PostNetworkModel>>
}