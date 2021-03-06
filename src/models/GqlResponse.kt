package com.rahul.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import java.util.*

@Serializable
data class GqlRecord(
    val response: String,
    val createdAt: Long,
    val updatedAt: Long,
    val opr: String,
    val tag: String?,
    @SerialName("_id")
    @BsonId val id: String = UUID.randomUUID().toString()
)

@Serializable
data class CreateGqlRecord(
    val response: String,
    val createdAt: Long,
    val updatedAt: Long,
    val opr: String,
    val tag: String?= null
)

fun CreateGqlRecord.toGqlRecord(): GqlRecord {
    return GqlRecord(
        response = response,
        createdAt = createdAt,
        updatedAt = updatedAt,
        opr = opr,
        tag = tag
    )
}

@Serializable
data class RestRecord(
    val response: String,
    val createdAt: Long,
    val updatedAt: Long,
    val tag: String?,
    val url: String,
    val httpMethod: String,
    @BsonId val id: String = UUID.randomUUID().toString()
)

@Serializable
data class CreateRestRecord(
    val response: String,
    val createdAt: Long,
    val updatedAt: Long,
    val tag: String? = null,
    val url: String,
    val httpMethod: String
)

fun CreateRestRecord.toRestRecord(): RestRecord {
    return RestRecord(
        response = response,
        createdAt = createdAt,
        updatedAt = updatedAt,
        tag = tag,
        url = url,
        httpMethod = httpMethod
    )
}