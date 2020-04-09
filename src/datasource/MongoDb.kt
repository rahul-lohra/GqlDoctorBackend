package com.rahul.datasource

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider
import org.litote.kmongo.KMongo

class MongoDb {

    val DB_USER = "rahullohra"
    val DB_PASSWORD = "rahul12345"
    val DATABASE_NAME = "rahul_blog_db"

    val CONNECTION_STRING = "mongodb://$DB_USER:$DB_PASSWORD@ds055990.mlab.com:55990/rahul_blog_db"
    lateinit var mongoClient: MongoClient
    lateinit var mongoDatabase: MongoDatabase

    init {
        connect()
    }

    fun connect() {
        setupKMongo()
    }

    fun setupKMongo(){
        val mongoSettings = MongoClientSettings.builder()
            .applyConnectionString(ConnectionString(CONNECTION_STRING))
            .retryWrites(false)
            .build()
        mongoClient = KMongo.createClient(mongoSettings)
        mongoDatabase = mongoClient.getDatabase(DATABASE_NAME)
    }

    fun setupNormalMongoDb(){
        mongoClient = MongoClients.create(CONNECTION_STRING)
        mongoDatabase = mongoClient.getDatabase(DATABASE_NAME)
    }

    fun getMongoSettings(): MongoClientSettings {
        val pojoCodecRegistry: CodecRegistry = CodecRegistries.fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
        )

        val mongoSettings = MongoClientSettings.builder()
            .codecRegistry(pojoCodecRegistry)
            .applyConnectionString(ConnectionString("mongodb://$DB_USER:$DB_PASSWORD@ds055990.mlab.com:55990/rahul_blog_db"))
            .build()
        return mongoSettings
    }


}