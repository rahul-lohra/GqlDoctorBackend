package com.rahul.models

import org.bson.codecs.pojo.annotations.BsonId
import java.util.*

data class Book(val title: String, val author: String, @BsonId val bookId: UUID = UUID.randomUUID())
data class CreateBookRequest(val title: String, val author: String)

fun CreateBookRequest.toBook(): Book {
    return Book(title, author)
}