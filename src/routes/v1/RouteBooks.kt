package com.rahul.routes.v1

import com.rahul.models.Book
import com.rahul.models.CreateBookRequest
import com.rahul.repository.BooksRepository
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.post
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import javax.inject.Inject

const val BOOKS = Constants.V1 + "/books"

class RouteBooks @Inject constructor (val booksRepository: BooksRepository, routing: Routing) {

    init {
        routing {
            get(BOOKS) {
                val books = booksRepository.getBooks()
                if (books != null)
                    call.respond(HttpStatusCode.OK, books)
                else
                    call.respond(HttpStatusCode.OK, emptyList<Book>())
            }

            post<CreateBookRequest> { request ->
                booksRepository.insertBook(request)
                call.respond(HttpStatusCode.OK)
            }

        }
    }
}