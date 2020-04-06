package com.rahul.repository

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.rahul.models.Book
import com.rahul.models.CreateBookRequest
import com.rahul.models.toBook
import javax.inject.Inject

class BooksRepository @Inject constructor(val mongoDatabase: MongoDatabase) {

    val COLLECTION_NAME = "books"

    fun insertBook(createBookRequest: CreateBookRequest) {
        getCollection().insertOne(createBookRequest.toBook())
    }

    fun getBooks(): List<Book>? {
        return getCollection().find().toList()
    }

    private fun getCollection(): MongoCollection<Book> {
        return mongoDatabase.getCollection(COLLECTION_NAME, Book::class.java)
    }
}