package com.rahul.repository

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.rahul.models.CreateGqlRecord
import com.rahul.models.GqlRecord
import com.rahul.models.toGqlRecord
import org.bson.BsonValue
import javax.inject.Inject

class GqlRecordRepository @Inject constructor(val mongoDatabase: MongoDatabase) {

    val COLLECTION_NAME = "gqlRecord"

    fun getAllItems(): List<GqlRecord> {
        return getCollection().find().toList()
    }

    fun insert(createGqlRecord: CreateGqlRecord):BsonValue?{
        return getCollection().insertOne(createGqlRecord.toGqlRecord()).insertedId
    }

    private fun getCollection(): MongoCollection<GqlRecord> {
        return mongoDatabase.getCollection(COLLECTION_NAME, GqlRecord::class.java)
    }
}