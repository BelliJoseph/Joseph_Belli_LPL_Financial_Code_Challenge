package com.example.lplfinancialcodechallenge.data.network

import com.google.gson.annotations.SerializedName

data class PostNetworkModel(
    @SerializedName("body")
    val body: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("postId")
    val postId: Int
)