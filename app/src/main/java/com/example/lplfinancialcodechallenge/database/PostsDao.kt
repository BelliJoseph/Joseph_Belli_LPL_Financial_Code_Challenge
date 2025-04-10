package com.example.lplfinancialcodechallenge.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lplfinancialcodechallenge.data.entity.PostEntityModel

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPosts(posts: List<PostEntityModel>)

    @Query("SELECT * FROM post_entity")
    suspend fun getAllPosts(): List<PostEntityModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(postEntityModel: PostEntityModel)
}