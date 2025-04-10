package com.example.lplfinancialcodechallenge.domain

import com.example.lplfinancialcodechallenge.data.domain.PostDomainModel
import com.example.lplfinancialcodechallenge.data.repository.PostsRepository
import com.example.lplfinancialcodechallenge.data.util.toEntity
import javax.inject.Inject

class UpdatePostUseCase @Inject constructor(
    private val postsRepository: PostsRepository
){

    suspend fun updatePost(
        post: PostDomainModel,
        imagePath: String
    ) {
        postsRepository.insertPost(
            postEntityModel = post.toEntity(imagePath = imagePath)
        )
    }
}