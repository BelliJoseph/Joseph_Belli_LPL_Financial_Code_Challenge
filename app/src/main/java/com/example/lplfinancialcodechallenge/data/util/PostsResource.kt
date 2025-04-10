package com.example.lplfinancialcodechallenge.data.util

import com.example.lplfinancialcodechallenge.data.domain.PostDomainModel
import com.example.lplfinancialcodechallenge.data.entity.PostEntityModel
import com.example.lplfinancialcodechallenge.data.network.PostNetworkModel

fun List<PostNetworkModel>.toEntityList(): List<PostEntityModel> {
    return this.map {
        PostEntityModel(
            id = it.id,
            name = it.name,
            email = it.email,
            body = it.body
        )
    }
}

fun List<PostEntityModel>.toDomainList(): List<PostDomainModel> {
    return this.map {
        PostDomainModel(
            id = it.id,
            name = it.name,
            email = it.email,
            body = it.body,
            image = it.image
        )
    }
}

fun PostDomainModel.toEntity(imagePath: String): PostEntityModel =
    PostEntityModel(
        id = this.id,
        name = this.name,
        email = this.email,
        body = this.body,
        image = imagePath
    )