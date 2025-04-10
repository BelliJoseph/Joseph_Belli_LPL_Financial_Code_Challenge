package com.example.lplfinancialcodechallenge.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_entity")
data class PostEntityModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
    val image: String? = null,
)
