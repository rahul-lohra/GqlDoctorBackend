package com.rahul.repository

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.rahul.models.Post
import com.rahul.models.RestRecord
import javax.inject.Inject


class RestRecordRepository @Inject constructor (val mongoDatabase: MongoDatabase) {

    val COLLECTION_NAME = "restRecord"

    fun getPosts():List<RestRecord> {
        return getCollection().find().toList()
    }

    private fun getCollection(): MongoCollection<RestRecord> {
        return mongoDatabase.getCollection(COLLECTION_NAME, RestRecord::class.java)
    }
}