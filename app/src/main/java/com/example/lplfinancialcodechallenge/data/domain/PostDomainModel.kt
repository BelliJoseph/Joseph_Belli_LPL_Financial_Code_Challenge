package com.example.lplfinancialcodechallenge.data.domain

data class PostDomainModel(
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
    val image: String? = null,
)
