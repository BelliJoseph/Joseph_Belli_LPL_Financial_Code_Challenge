package com.example.lplfinancialcodechallenge.domain

import com.example.lplfinancialcodechallenge.data.domain.PostDomainModel
import com.example.lplfinancialcodechallenge.data.repository.PostsRepository
import com.example.lplfinancialcodechallenge.data.util.toDomainList
import com.example.lplfinancialcodechallenge.data.util.toEntityList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository,
    private val dispatcher: CoroutineDispatcher
) {

    fun getPosts(): Flow<UiState<List<PostDomainModel>>> = flow {
        emit(UiState.LOADING)
        try {
            val entityList = postsRepository.getAllPostsDB()

            if (entityList.isNotEmpty()) {
                emit(UiState.SUCCESS(entityList.toDomainList()))
            } else {

                val result = postsRepository.getNetworkPosts()

                if (result.isSuccessful) {
                    result.body()?.let {
                        postsRepository.insertAllPostsDB(it.toEntityList())

                        val entities = postsRepository.getAllPostsDB()

                        if (entities.isNotEmpty()) {
                            emit(UiState.SUCCESS(entities.toDomainList()))
                        } else {
                            throw Exception("Posts are empty")
                        }

                    } ?: throw Exception("Posts are empty")

                } else {
                    throw Exception(result.errorBody()?.string())
                }
            }

        } catch (e: Exception) {
            emit(UiState.ERROR(e))
        }

    }.flowOn(dispatcher)
}