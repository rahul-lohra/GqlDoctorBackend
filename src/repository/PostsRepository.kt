package com.rahul.repository

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.rahul.models.Post
import javax.inject.Inject

class PostsRepository @Inject constructor (val mongoDatabase: MongoDatabase) {

    val COLLECTION_NAME = "posts"

    fun getPosts():List<Post> {
        return getCollection().find().toList()
    }

    private fun getCollection(): MongoCollection<Post> {
        return mongoDatabase.getCollection(COLLECTION_NAME, Post::class.java)
    }
}