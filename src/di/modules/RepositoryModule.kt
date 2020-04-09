package com.rahul.di.modules

import com.mongodb.client.MongoDatabase
import com.rahul.repository.BooksRepository
import com.rahul.repository.GqlRecordRepository
import com.rahul.repository.PostsRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(mongoDatabase: MongoDatabase):PostsRepository{
        return PostsRepository(mongoDatabase)
    }

    @Provides
    fun provideBookRepository(mongoDatabase: MongoDatabase):BooksRepository{
        return BooksRepository(mongoDatabase)
    }

    @Provides
    fun provideGqlRepository(mongoDatabase: MongoDatabase):GqlRecordRepository{
        return GqlRecordRepository(mongoDatabase)
    }
}