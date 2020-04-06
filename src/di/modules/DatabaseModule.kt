package com.rahul.di.modules

import com.mongodb.client.MongoDatabase
import com.rahul.datasource.MongoDb
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun setupMongo():MongoDb{
        return MongoDb()
    }

    @Provides
    fun provideDatabase(mongoDb: MongoDb): MongoDatabase {
        return mongoDb.mongoDatabase
    }
}