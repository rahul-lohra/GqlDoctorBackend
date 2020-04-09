package com.rahul.repository

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.rahul.models.*
import org.bson.BsonValue
import javax.inject.Inject


class RestRecordRepository @Inject constructor (val mongoDatabase: MongoDatabase) {

    val COLLECTION_NAME = "restRecord"

    fun getAllItems(): List<RestRecord> {
        return getCollection().find().toList()
    }

    fun insert(record: CreateRestRecord): BsonValue?{
        return getCollection().insertOne(record.toRestRecord()).insertedId
    }

    private fun getCollection(): MongoCollection<RestRecord> {
        return mongoDatabase.getCollection(COLLECTION_NAME, RestRecord::class.java)
    }
}