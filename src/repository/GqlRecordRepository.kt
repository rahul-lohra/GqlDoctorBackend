package com.rahul.repository

import com.mongodb.BasicDBObject
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.rahul.models.CreateGqlRecord
import com.rahul.models.GqlRecord
import com.rahul.models.toGqlRecord
import io.ktor.http.Parameters
import org.bson.BsonValue
import javax.inject.Inject

class GqlRecordRepository @Inject constructor(val mongoDatabase: MongoDatabase) {

    val COLLECTION_NAME = "gqlRecord"
    val filterableKeys = arrayListOf(GqlRecord::opr.name, GqlRecord::tag.name)

    fun getAllItems(queryParameters: Parameters): List<GqlRecord> {
        val whereQuery = BasicDBObject()
        filterableKeys.forEach {
            val paramValue = queryParameters[it]
            if (!paramValue.isNullOrEmpty()) {
                whereQuery[it] = BasicDBObject("\$regex", "$paramValue.*")
            }
        }

        return getCollection().find(whereQuery).toList()
    }

    fun insert(createGqlRecord: CreateGqlRecord): BsonValue? {
        return getCollection().insertOne(createGqlRecord.toGqlRecord()).insertedId
    }

    private fun getCollection(): MongoCollection<GqlRecord> {
        return mongoDatabase.getCollection(COLLECTION_NAME, GqlRecord::class.java)
    }

    /*
    *
    * val whereQuery = BasicDBObject()
        if (!operationName.isNullOrEmpty())
            whereQuery[GqlRecord::opr.name] = BasicDBObject("\$regex", "$operationName.*")
        if (!tag.isNullOrEmpty())
            whereQuery[GqlRecord::tag.name] = BasicDBObject("\$regex", "$tag.*")
    * */
}