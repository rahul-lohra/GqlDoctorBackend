package com.rahul.repository

import com.mongodb.BasicDBObject
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.rahul.models.*
import io.ktor.http.Parameters
import org.bson.BsonValue
import javax.inject.Inject


class RestRecordRepository @Inject constructor (val mongoDatabase: MongoDatabase) {

    private val COLLECTION_NAME = "restRecord"
    private val filterableKeys = arrayListOf(GqlRecord::opr.name, GqlRecord::tag.name)

    fun getAllItems(queryParameters: Parameters): List<RestRecord> {
        val whereQuery = BasicDBObject()
        filterableKeys.forEach {
            val paramValue = queryParameters[it]
            if (!paramValue.isNullOrEmpty()) {
                whereQuery[it] = BasicDBObject("\$regex", "$paramValue.*")
            }
        }

        return getCollection().find(whereQuery).toList()
    }

    fun insert(record: CreateRestRecord): BsonValue?{
        return getCollection().insertOne(record.toRestRecord()).insertedId
    }

    private fun getCollection(): MongoCollection<RestRecord> {
        return mongoDatabase.getCollection(COLLECTION_NAME, RestRecord::class.java)
    }
}